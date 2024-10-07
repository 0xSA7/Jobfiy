package com.sa7.jobfiy.authentication

import android.util.Patterns.EMAIL_ADDRESS


// Validator class to validate the email, password, name, and confirm password
object Validator {

    // isValidEmail function to validate the email
    fun isValidEmail(email: String): ValidationResult {
        return ValidationResult(
            // check if the email is not empty and matches the email pattern
            (email.isNotEmpty() && EMAIL_ADDRESS.matcher(email).matches())
        )
    }

    fun isValidPassword(password: String): ValidationResult {
        return ValidationResult(
            // check if the password is not empty and has more than 6 characters
            (password.isNotEmpty() && password.length >= 6)
        )
    }

    fun isValidName(name: String): ValidationResult {
        return ValidationResult(
            // check if the name is not empty and has more than 3 characters
            (name.isNotEmpty() && name.length >= 3)
        )
    }

    fun isValidConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return ValidationResult(
            // check if the password and confirm password are same
            (password == confirmPassword)
        )
    }
}

// ValidationResult data class to return the validation status of the data fields
data class ValidationResult(
    // status to check if the data field is valid or not
    val status: Boolean = false,
)