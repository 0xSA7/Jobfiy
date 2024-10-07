package com.sa7.jobfiy.authentication.ui.screens.Login

sealed class LoginUiEvent {
    // Events that can be triggered by the UI
    data class EmailChanged(val value: String) : LoginUiEvent()
    data class PasswordChanged(val value: String) : LoginUiEvent()

    object LoginButtonClicked : LoginUiEvent()
    object ResetPasswordButtonClicked : LoginUiEvent()
}