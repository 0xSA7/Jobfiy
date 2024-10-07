package com.sa7.jobfiy.authentication.ui.screens.Login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.sa7.jobfiy.authentication.Validator
import com.sa7.jobfiy.authentication.ui.navigation.AppRoute
import com.sa7.jobfiy.authentication.ui.navigation.Screen

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

    // login the user to the app using email and password
    private fun userLogin(email: String, password: String) {
        loginProgress.value = true
        // Sign in with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    loginProgress.value = false

                    // Navigate to the home screen
                    AppRoute.navigateTo(Screen.HomeScreen)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
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