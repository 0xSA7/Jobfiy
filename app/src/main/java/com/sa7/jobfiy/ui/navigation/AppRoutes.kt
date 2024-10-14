package com.sa7.jobfiy.ui.navigation

import JobDetailPage
import JobifyScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sa7.jobfiy.authentication.ui.screens.HomeScreen
import com.sa7.jobfiy.authentication.ui.screens.login.LoginScreen
import com.sa7.jobfiy.authentication.ui.screens.login.LoginViewModel
import com.sa7.jobfiy.authentication.ui.screens.resetPassword.ResetPasswordScreen
import com.sa7.jobfiy.authentication.ui.screens.signUp.SignUpScreen
import com.sa7.jobfiy.authentication.ui.screens.signUp.SignUpViewModel
import java.lang.reflect.Modifier

object Routes {
    const val SIGN_UP = "signup"
    const val LOGIN = "login"
    const val RESET_PASSWORD = "reset_password"
    const val JOBIFY_SCREEN = "jobify_screen"
    const val JOB_DESCRIPTION = "job_description"

}

@Composable
fun AppNavHost(loginViewModel: LoginViewModel = viewModel()) {
    var startDest = Routes.LOGIN
    if(loginViewModel.isUserLoggedInLiveData.value == true)
    {
        startDest= Routes.JOBIFY_SCREEN

    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDest) {
        composable(route = Routes.SIGN_UP) { SignUpScreen(navController) }
        composable(route = Routes.LOGIN) { LoginScreen(navController) }
        composable(route = Routes.RESET_PASSWORD) { ResetPasswordScreen(navController) }
        composable(route = Routes.JOBIFY_SCREEN) { JobifyScreen(navController) }
        composable(route = Routes.JOB_DESCRIPTION) { JobDetailPage(navController) }

    }
}