package com.sa7.jobfiy.ui.navigation

import JobifyScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sa7.jobfiy.ui.CommonUi.BottomBarScreen
import com.sa7.jobfiy.ui.profile.JobfiyProfileScreen
import com.sa7.jobfiy.ui.save.SaveScreenContent


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
    composable (route = BottomBarScreen.Home.route){
        JobifyScreen()
    }
        composable (route = BottomBarScreen.Saved.route){
            SaveScreenContent()
    }
        composable (route = BottomBarScreen.Profile.route){
            JobfiyProfileScreen()
    }

    }
}