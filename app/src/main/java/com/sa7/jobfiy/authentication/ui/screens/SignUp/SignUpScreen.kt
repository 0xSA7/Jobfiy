package com.sa7.jobfiy.authentication.ui.screens.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sa7.jobfiy.R
import com.sa7.jobfiy.authentication.ui.component.ButtonComponent
import com.sa7.jobfiy.authentication.ui.component.ClickableTextComponent
import com.sa7.jobfiy.authentication.ui.component.DividerTextComponent
import com.sa7.jobfiy.authentication.ui.component.GoogleButtonComponent
import com.sa7.jobfiy.authentication.ui.component.HeadingTextComponent
import com.sa7.jobfiy.authentication.ui.component.PasswordTextFieldComponent
import com.sa7.jobfiy.authentication.ui.component.TextFieldComponent
import com.sa7.jobfiy.authentication.ui.component.NormalTextComponent
import com.sa7.jobfiy.authentication.ui.navigation.AppRoute
import com.sa7.jobfiy.authentication.ui.navigation.Screen

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                NormalTextComponent("Hey there,")
                HeadingTextComponent("Create an account")

                // First Name Field
                TextFieldComponent(
                    "First Name", painterResource(id = R.drawable.person_asset), onTextSelected = {
                        // listen to the text change event and update the view model
                        signUpViewModel.onEvent(SignUpUiEvent.FirstNameChanged(it))
                    },
                    // update the error status and error message based on the validation
                    errorStatus = signUpViewModel.registrationUiState.value.firstNameError,
                    errorMessage = signUpViewModel.registrationUiState.value.nameErrorMessage,
                    )

                // Last Name Field
                TextFieldComponent(
                    "Last Name", painterResource(id = R.drawable.person_asset), onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.lastNameError,
                    errorMessage = signUpViewModel.registrationUiState.value.nameErrorMessage
                )

                // Email Field
                TextFieldComponent(
                    "Email", painterResource(id = R.drawable.email_asset), onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.EmailChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.emailError,
                    errorMessage = signUpViewModel.registrationUiState.value.emailErrorMessage
                )

                // Password Field
                PasswordTextFieldComponent(
                    "Password", false, onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.passwordError,
                    errorMessage = signUpViewModel.registrationUiState.value.passwordErrorMessage
                )

                // Confirm Password Field
                PasswordTextFieldComponent(
                    "Confirm Password",
                    // to check if this last field or not
                    true,
                    // listen to the text change event and update the view model
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.ConfirmPasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.confirmPasswordError,
                    errorMessage = signUpViewModel.registrationUiState.value.confirmPasswordErrorMessage
                )
                Spacer(modifier = Modifier.heightIn(80.dp))
                ButtonComponent(
                    "Sign Up") {
                    signUpViewModel.onEvent(SignUpUiEvent.RegisterButtonClicked)
                }
                Spacer(modifier = Modifier.heightIn(10.dp))
                DividerTextComponent()
                Spacer(modifier = Modifier.heightIn(10.dp))
                GoogleButtonComponent()
                ClickableTextComponent("Already have an account? Sign In") {
                    AppRoute.navigateTo(Screen.LoginScreen)
                }
            }
        }

        // Progress Indicator to show the registration progress
        if (signUpViewModel.registrationProgress.value) {
            CircularProgressIndicator(
                color = Color.Black,
                strokeWidth = 5.dp
            )
        }
    }

}
