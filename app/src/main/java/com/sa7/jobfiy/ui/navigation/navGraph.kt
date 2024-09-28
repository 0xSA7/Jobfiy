package com.sa7.jobfiy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sa7.jobfiy.ui.screens.LoginScreen
import com.sa7.jobfiy.ui.screens.SignUpScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "signup"
    ) {
        composable("signup") {
            SignUpScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
    }
}
