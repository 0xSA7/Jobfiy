package com.sa7.jobfiy.authentication.ui.screens.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.sa7.jobfiy.authentication.Validator

class LoginViewModel : ViewModel() {

    // State of the UI
    var loginUiState = mutableStateOf(LoginUiState())
    // State of the validation
    private var allValidationPassed = mutableStateOf(false)
    // State of the login progress
    var loginProgress = mutableStateOf(false)


    // Handle the events from the UI
    fun onEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            // Handle the email change event
            is LoginUiEvent.EmailChanged -> {
                // Update the email in the state
                loginUiState.value = loginUiState.value.copy(email = loginUiEvent.value)
                printState()
            }

            // Handle the password change event
            is LoginUiEvent.PasswordChanged -> {
                // Update the password in the state
                loginUiState.value = loginUiState.value.copy(password = loginUiEvent.value)
                printState()
            }

            // Handle the login button click event
            is LoginUiEvent.LoginButtonClicked -> {
                // check the login data and login
                login()
                printState()
            }

            // Handle the reset password button click event
            is LoginUiEvent.ResetPasswordButtonClicked -> {
                // check the email and reset the password
                resetPassword(loginUiState.value.email)
                printState()
            }
        }
    }

    private fun login() {
        // Validate the login data
        validateLoginDataWithRules()
        // If all the validation passed, login
        if (allValidationPassed.value) {
            userLogin(loginUiState.value.email, loginUiState.value.password)
        }
    }

    private fun validateLoginDataWithRules() {
        // Validate the email and password
        val email = Validator.isValidEmail(loginUiState.value.email)
        val password = Validator.isValidPassword(loginUiState.value.password)


        // Update the state with the validation result
        loginUiState.value = loginUiState.value.copy(
            emailError = !email.status,
            passwordError = !password.status,
        )
        // Update the allValidationPassed state
        allValidationPassed.value = email.status && password.status
    }

    private fun printState() {
        Log.d("LoginViewModel", "State: ${loginUiState.value}")
    }


    // Validate the email and reset the password
    private fun validResetPasswordEmail() {
        val email = Validator.isValidEmail(loginUiState.value.email)
        loginUiState.value = loginUiState.value.copy(
            emailError = !email.status
        )
        allValidationPassed.value = email.status
    }

    private val isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedInLiveData: LiveData<Boolean> = isUserLoggedIn
    private val emailId = MutableLiveData<String>()
    val emailIdLiveData: LiveData<String> = emailId

    private fun userLogin(email: String, password: String) {
        // Start login progress
        loginProgress.value = true
        val auth = FirebaseAuth.getInstance()

        // Authenticate the user
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User logged in successfully, now check for email verification
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        // Email is verified, proceed to the next step (e.g., navigate to home screen)
                        Log.d(TAG, "Login successful. Email is verified.")
                        loginProgress.value = false
                        emailId.value = email
                        isUserLoggedIn.value = true
                        // Navigate to the next screen

                    } else {
                        // Email not verified
                        Log.w(TAG, "Email not verified. Please verify your email.")
                        loginProgress.value = false
                        // Optionally sign out the user
                        auth.signOut()
                    }
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    loginProgress.value = false
                }
            }
    }


    private fun resetPassword(email: String) {
        // Validate the email
        validResetPasswordEmail()
        if (allValidationPassed.value) {
            // Send a password reset email
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                        // Navigate to the login screen
                    } else {
                        Log.w(TAG, "Email not sent.", task.exception)
                    }
                }
        }

    }
}