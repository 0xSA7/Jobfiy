package com.sa7.jobfiy.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var registrationUiState = mutableStateOf(RegistrationUiState())

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.FirstNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(firstName = uiEvent.value)
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
                registrationUiState.value = registrationUiState.value.copy(confirmPassword = uiEvent.value)
                printState()
            }
        }
    }

    private fun printState() {
        Log.d("LoginViewModel", "State: ${registrationUiState.value.toString()}")

    }
}