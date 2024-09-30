package com.sa7.jobfiy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.sa7.jobfiy.ui.component.NormalTextComponent
import com.sa7.jobfiy.ui.component.PasswordTextFieldComponent
import com.sa7.jobfiy.ui.component.TextFieldComponent
import com.sa7.jobfiy.ui.component.UnderlinedTextComponent

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
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
            NormalTextComponent("Welcome back,")
            HeadingTextComponent("Login to your account")

            TextFieldComponent("Email", painterResource(id = R.drawable.email_asset), onTextSelected = {
                loginViewModel.onEvent(UiEvent.EmailChanged(it))
            },
                errorStatus = loginViewModel.registrationUiState.value.emailError
            )
            PasswordTextFieldComponent("Password", true, onTextSelected = {
                loginViewModel.onEvent(UiEvent.PasswordChanged(it))
            },
                errorStatus = loginViewModel.registrationUiState.value.passwordError
            )
            Spacer(modifier = Modifier.heightIn(10.dp))
            UnderlinedTextComponent("Forgot your password") { }
            Spacer(modifier = Modifier.heightIn(80.dp))
            // not handled yet!!
            ButtonComponent("Login") {
                loginViewModel.onEvent(UiEvent.RegisterButtonClicked)
            }
            Spacer(modifier = Modifier.heightIn(10.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.heightIn(10.dp))
            GoogleButtonComponent()
            ClickableTextComponent("Don't have an account? Sign Up") {
                navController.navigate("signup")
            }
        }
    }
}
