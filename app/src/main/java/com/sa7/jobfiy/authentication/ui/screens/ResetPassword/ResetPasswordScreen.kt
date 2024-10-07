package com.sa7.jobfiy.authentication.ui.screens.ResetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sa7.jobfiy.R
import com.sa7.jobfiy.authentication.ui.component.ButtonComponent
import com.sa7.jobfiy.authentication.ui.component.TextFieldComponent
import com.sa7.jobfiy.authentication.ui.screens.Login.LoginUiEvent
import com.sa7.jobfiy.authentication.ui.screens.Login.LoginViewModel

@Composable
fun ResetPasswordScreen(loginViewModel: LoginViewModel = viewModel()) {
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Reset password screen content
                TextFieldComponent("Email", painterResource(id = R.drawable.email_asset), onTextSelected = {
                    loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))
                },
                    errorStatus = loginViewModel.loginUiState.value.emailError,
                    errorMessage = "Please enter a valid email address"
                )

                Spacer(modifier = Modifier.heightIn(16.dp))

                ButtonComponent("Send Email") {
                    // Handle the send email button click event
                    loginViewModel.onEvent(LoginUiEvent.ResetPasswordButtonClicked)
                }
            }

        }
    }
}