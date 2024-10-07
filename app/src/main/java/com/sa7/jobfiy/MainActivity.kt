package com.sa7.jobfiy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sa7.jobfiy.app.JobfiyApp
import com.sa7.jobfiy.authentication.ui.screens.Login.LoginScreen
import com.sa7.jobfiy.authentication.ui.screens.ResetPassword.ResetPasswordScreen
import com.sa7.jobfiy.authentication.ui.screens.SignUp.SignUpScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResetPasswordScreen()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun JobfiyPreview() {
    JobfiyApp()
}