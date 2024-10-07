package com.sa7.jobfiy.authentication.ui.screens.SignUp

data class RegistrationUiState(
    // Registration Form Fields and Error Fields for Validation and Error Handling
    var firstName  : String = "",
    var lastName  : String = "",
    var email  : String = "",
    var password  : String = "",
    var confirmPassword  : String = "",

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false,
    var confirmPasswordError: Boolean = false,

    val emailErrorMessage: String = "Invalid email",
    val passwordErrorMessage: String = "Password should be more than 6 characters",
    val nameErrorMessage: String = "Name should be more than 3 characters",
    val confirmPasswordErrorMessage: String = "Password and Confirm Password should be same"
)