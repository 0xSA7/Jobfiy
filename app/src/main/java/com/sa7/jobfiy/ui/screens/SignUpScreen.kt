package com.sa7.jobfiy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sa7.jobfiy.R
import com.sa7.jobfiy.data.LoginViewModel
import com.sa7.jobfiy.data.UiEvent
import com.sa7.jobfiy.ui.component.ButtonComponent
import com.sa7.jobfiy.ui.component.ClickableTextComponent
import com.sa7.jobfiy.ui.component.DividerTextComponent
import com.sa7.jobfiy.ui.component.GoogleButtonComponent
import com.sa7.jobfiy.ui.component.HeadingTextComponent
import com.sa7.jobfiy.ui.component.PasswordTextFieldComponent
import com.sa7.jobfiy.ui.component.TextFieldComponent
import com.sa7.jobfiy.ui.component.NormalTextComponent

@Composable
fun SignUpScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
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

            TextFieldComponent(
                "First Name", painterResource(id = R.drawable.person_asset), onTextSelected = {
                    loginViewModel.onEvent(UiEvent.FirstNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.firstNameError,
                errorMessage = loginViewModel.registrationUiState.value.nameErrorMessage,

            )
            TextFieldComponent(
                "Last Name", painterResource(id = R.drawable.person_asset), onTextSelected = {
                    loginViewModel.onEvent(UiEvent.LastNameChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.lastNameError,
                errorMessage = loginViewModel.registrationUiState.value.nameErrorMessage
            )
            TextFieldComponent(
                "Email", painterResource(id = R.drawable.email_asset), onTextSelected = {
                    loginViewModel.onEvent(UiEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.emailError,
                errorMessage = loginViewModel.registrationUiState.value.emailErrorMessage
            )
            PasswordTextFieldComponent(
                "Password", false, onTextSelected = {
                    loginViewModel.onEvent(UiEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.passwordError,
                errorMessage = loginViewModel.registrationUiState.value.passwordErrorMessage
            )
            PasswordTextFieldComponent(
                "Confirm Password", true, onTextSelected = {
                    loginViewModel.onEvent(UiEvent.ConfirmPasswordChanged(it))
                },
                errorStatus = loginViewModel.registrationUiState.value.confirmPasswordError,
                errorMessage = loginViewModel.registrationUiState.value.confirmPasswordErrorMessage
            )
            Spacer(modifier = Modifier.heightIn(80.dp))
            ButtonComponent("Sign Up") {
                loginViewModel.onEvent(UiEvent.RegisterButtonClicked)
            }
            Spacer(modifier = Modifier.heightIn(10.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.heightIn(10.dp))
            GoogleButtonComponent()
            ClickableTextComponent("Already have an account? Sign In") {
                navController.navigate("login")
            }
        }
    }
}
