package com.sa7.jobfiy.authentication.ui.screens.Login

data class LoginUiState(

    // The state of the login screen
    var email  : String = "",
    var password  : String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,

    val emailErrorMessage: String = "Invalid email",
    val passwordErrorMessage: String = "Password should be more than 6 characters",
)