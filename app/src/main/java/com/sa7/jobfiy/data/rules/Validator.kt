package com.sa7.jobfiy.data.rules

import android.util.Patterns.EMAIL_ADDRESS

object Validator {
    fun isValidEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty() && EMAIL_ADDRESS.matcher(email).matches())
        )
    }

    fun isValidPassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length > 6)
        )
    }

    fun isValidName(name: String): ValidationResult {
        return ValidationResult(
            (name.isNotEmpty() && name.length > 3)
        )
    }

    fun isValidConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return ValidationResult(
            (password == confirmPassword)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false,
)