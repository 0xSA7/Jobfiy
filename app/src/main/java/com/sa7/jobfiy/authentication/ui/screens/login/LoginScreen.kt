package com.sa7.jobfiy.authentication.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sa7.jobfiy.R
import com.sa7.jobfiy.authentication.ui.component.ButtonComponent
import com.sa7.jobfiy.authentication.ui.component.ClickableTextComponent
import com.sa7.jobfiy.authentication.ui.component.DividerTextComponent
import com.sa7.jobfiy.authentication.ui.component.GoogleButtonComponent
import com.sa7.jobfiy.authentication.ui.component.HeadingTextComponent
import com.sa7.jobfiy.authentication.ui.component.NormalTextComponent
import com.sa7.jobfiy.authentication.ui.component.PasswordTextFieldComponent
import com.sa7.jobfiy.authentication.ui.component.TextFieldComponent
import com.sa7.jobfiy.authentication.ui.component.UnderlinedTextComponent
import com.sa7.jobfiy.ui.navigation.Routes


@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {

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
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                NormalTextComponent("Welcome back,")
                HeadingTextComponent("Login to your account")

                TextFieldComponent("Email", painterResource(id = R.drawable.email_asset), onTextSelected = {
                    // listen to email changes
                    loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))
                },
                    // get email error status from viewmodel
                    errorStatus = loginViewModel.loginUiState.value.emailError
                )
                PasswordTextFieldComponent("Password", true, onTextSelected = {
                    // listen to password changes
                    loginViewModel.onEvent(LoginUiEvent.PasswordChanged(it))
                },
                    // get password error status from viewmodel
                    errorStatus = loginViewModel.loginUiState.value.passwordError
                )
                Spacer(modifier = Modifier.heightIn(10.dp))
                UnderlinedTextComponent("Forgot your password", onClickButton = {navController.navigate(Routes.RESET_PASSWORD)})
                Spacer(modifier = Modifier.heightIn(80.dp))
                ButtonComponent("Login") {
                    loginViewModel.onEvent(LoginUiEvent.LoginButtonClicked)
                    if(loginViewModel.isUserLoggedInLiveData.value == true){
                        navController.navigate(Routes.JOBIFY_SCREEN)
                    }

                }
                Spacer(modifier = Modifier.heightIn(10.dp))
                DividerTextComponent()
                Spacer(modifier = Modifier.heightIn(10.dp))
                GoogleButtonComponent()
                ClickableTextComponent("Don't have an account? Sign Up") {
                     navController.navigate(Routes.SIGN_UP)
                }
            }
        }

        // show progress indicator when login is in progress
        if (loginViewModel.loginProgress.value) {
            CircularProgressIndicator(
                color = Color.Black,
                strokeWidth = 5.dp
            )
        }

    }

}
