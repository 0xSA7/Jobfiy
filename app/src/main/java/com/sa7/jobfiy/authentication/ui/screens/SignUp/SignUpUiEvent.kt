package com.sa7.jobfiy.authentication.ui.screens.SignUp

sealed class SignUpUiEvent {
    // Events for the SignUp Screen
    data class FirstNameChanged(val value: String) : SignUpUiEvent()
    data class LastNameChanged(val value: String) : SignUpUiEvent()
    data class EmailChanged(val value: String) : SignUpUiEvent()
    data class PasswordChanged(val value: String) : SignUpUiEvent()
    data class ConfirmPasswordChanged(val value: String) : SignUpUiEvent()

    // Event for the Register Button Clicked
    object RegisterButtonClicked : SignUpUiEvent()
}