package com.sa7.jobfiy.authentication.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen{
     object LoginScreen: Screen()
     object SignUpScreen: Screen()
     object HomeScreen: Screen()
}

object AppRoute {
    private var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}