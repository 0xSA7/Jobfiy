package com.sa7.jobfiy.data

sealed class UiEvent {
    data class FirstNameChanged(val value: String) : UiEvent()
    data class LastNameChanged(val value: String) : UiEvent()
    data class EmailChanged(val value: String) : UiEvent()
    data class PasswordChanged(val value: String) : UiEvent()
    data class ConfirmPasswordChanged(val value: String) : UiEvent()
}