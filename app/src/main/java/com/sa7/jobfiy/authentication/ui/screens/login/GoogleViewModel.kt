package com.sa7.jobfiy.authentication.ui.screens.login

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import android.util.Log

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    // Firebase Auth instance
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    // Google Sign-In client
    private lateinit var googleSignInClient: GoogleSignInClient

    init {
        // Initialize Google Sign-In options and client
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // get from google-services.json file
            .requestIdToken("175078650895-1vri3kktg80g6e25v52r3logv04kpmfv.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Initialize Google Sign-In client
        googleSignInClient = GoogleSignIn.getClient(application.applicationContext, gso)
    }

    // Function to launch the Google Sign-In intent
    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    // Function to handle the sign-in result
    fun handleGoogleSignInResult(data: Intent?, onSuccess: (user: FirebaseAuth) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                account?.let {
                    firebaseAuthWithGoogle(it.idToken!!, onSuccess, onFailure)
                }
            } catch (e: ApiException) {
                Log.w("Google Sign-In", "Google sign in failed", e)
                onFailure(e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String, onSuccess: (user: FirebaseAuth) -> Unit, onFailure: (Exception) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase", "signInWithCredential:success")
                    onSuccess(auth)
                } else {
                    Log.w("Firebase", "signInWithCredential:failure", task.exception)
                    onFailure(task.exception ?: Exception("Unknown error"))
                }
            }
    }
}
