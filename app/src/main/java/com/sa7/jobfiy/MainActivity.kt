package com.sa7.jobfiy

import JobifyScreen
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sa7.jobfiy.ui.Onboarding.OnboardingPager
import com.sa7.jobfiy.ui.theme.JobfiyTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            JobfiyTheme {

                val navController = rememberNavController()
                NavHost(navController, startDestination = "onboarding") {
                    composable("onboarding") {
                        OnboardingScreenFlow(navController)
                    }
                    composable("jobify") {
                        JobifyScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingScreenFlow(navController: NavHostController) {
    val onFinish: () -> Unit = {
        navController.navigate("jobify") {
            popUpTo("onboarding") { inclusive = true }
        }
    }
    OnboardingPager(onFinish = onFinish)
}
