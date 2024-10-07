package com.sa7.jobfiy.authentication.ui.screens.signUp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.sa7.jobfiy.authentication.Validator

class SignUpViewModel : ViewModel() {
    // registrationUiState is a mutable state of RegistrationUiState
    var registrationUiState = mutableStateOf(RegistrationUiState())
    // to check if all the validation passed
    private var allValidationPassed = mutableStateOf(false)
    // to check if the registration is in progress
    var registrationProgress = mutableStateOf(false)

    // onEvent function to handle the events from the SignUpUiEvent
    fun onEvent(signUpUiEvent: SignUpUiEvent) {
        when (signUpUiEvent) {
            // update the field in the registrationUiState based on the event
            is SignUpUiEvent.FirstNameChanged -> {
                registrationUiState.value =
                    registrationUiState.value.copy(firstName = signUpUiEvent.value)
                printState()
            }

            is SignUpUiEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(lastName = signUpUiEvent.value)
                printState()
            }

            is SignUpUiEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(email = signUpUiEvent.value)
                printState()
            }

            is SignUpUiEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(password = signUpUiEvent.value)
                printState()
            }

            is SignUpUiEvent.ConfirmPasswordChanged -> {
                registrationUiState.value =
                    registrationUiState.value.copy(confirmPassword = signUpUiEvent.value)
                printState()
            }

            // when the register button is clicked, validate the data and sign up
            is SignUpUiEvent.RegisterButtonClicked -> {
                signUp()
                printState()
            }

            else -> {}
        }
    }

    // signUp function to validate the data and sign up the user
    private fun signUp() {
        // validate the data with the rules
        validateDataWithRules()
        // if all the validation passed, create the user in firebase
        if (allValidationPassed.value) {
            createUserInFirebase(registrationUiState.value.email, registrationUiState.value.password)
        }
    }


    // validateDataWithRules function to validate the data with the rules
    private fun validateDataWithRules() {
        // validate the first name, last name, email, password, and confirm password
        val fName = Validator.isValidName(registrationUiState.value.firstName)
        val lName = Validator.isValidName(registrationUiState.value.lastName)
        val email = Validator.isValidEmail(registrationUiState.value.email)
        val password = Validator.isValidPassword(registrationUiState.value.password)
        val confirmPassword = Validator.isValidConfirmPassword(
            registrationUiState.value.password,
            registrationUiState.value.confirmPassword
        )

        // update the error status based on the validation
        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = !fName.status,
            lastNameError = !lName.status,
            emailError = !email.status,
            passwordError = !password.status,
            confirmPasswordError = !confirmPassword.status
        )

        // if all the validation passed, set allValidationPassed to true
        if(fName.status && lName.status && email.status && password.status && confirmPassword.status) {
            allValidationPassed.value = true
        } else {
            allValidationPassed.value = false
        }
    }

    private fun printState() {
        Log.d("SignUpViewModel", "State: ${registrationUiState.value}")

    }


    // createUserInFirebase function to create the user in firebase
    private fun createUserInFirebase(email: String, password: String) {
        // set the registrationProgress to true
        registrationProgress.value = true
        // get the firebase auth instance
        val auth = FirebaseAuth.getInstance()

        // First, create the user
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User successfully created, now send verification email
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                        if (emailTask.isSuccessful) {
                            Log.d(TAG, "Email verification sent.")
                            registrationProgress.value = false

                            // Notify the user to check their email
                            Log.d(TAG, "Please verify your email before logging in.")

                            // Sign out the user after sending the verification email
                            auth.signOut()
                        } else {
                            Log.w(TAG, "sendEmailVerification:failure", emailTask.exception)
                            registrationProgress.value = false
                        }
                    }
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    registrationProgress.value = false
                }
            }
    }
}