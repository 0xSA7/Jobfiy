package com.sa7.jobfiy.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sa7.jobfiy.data.rules.Validator

class LoginViewModel : ViewModel() {
    var registrationUiState = mutableStateOf(RegistrationUiState())

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.FirstNameChanged -> {
                registrationUiState.value =
                    registrationUiState.value.copy(firstName = uiEvent.value)
                printState()
            }

            is UiEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(lastName = uiEvent.value)
                printState()
            }

            is UiEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(email = uiEvent.value)
                printState()
            }

            is UiEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(password = uiEvent.value)
                printState()
            }

            is UiEvent.ConfirmPasswordChanged -> {
                registrationUiState.value =
                    registrationUiState.value.copy(confirmPassword = uiEvent.value)
                printState()
            }

            is UiEvent.RegisterButtonClicked -> {
                signUp()
                printState()
            }
        }
    }

    private fun signUp() {
        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val fName = Validator.isValidName(registrationUiState.value.firstName)
        val lName = Validator.isValidName(registrationUiState.value.lastName)
        val email = Validator.isValidEmail(registrationUiState.value.email)
        val password = Validator.isValidPassword(registrationUiState.value.password)
        val confirmPassword = Validator.isValidConfirmPassword(
            registrationUiState.value.password,
            registrationUiState.value.confirmPassword
        )

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = !fName.status,
            lastNameError = !lName.status,
            emailError = !email.status,
            passwordError = !password.status,
            confirmPasswordError = !confirmPassword.status
        )
    }

    private fun printState() {
        Log.d("LoginViewModel", "State: ${registrationUiState.value.toString()}")

    }
}