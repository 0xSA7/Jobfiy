package com.sa7.jobfiy
import JobDetailPage
import JobifyScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.sa7.jobfiy.app.JobfiyApp
import com.sa7.jobfiy.authentication.ui.screens.login.LoginScreen





import com.sa7.jobfiy.ui.theme.JobfiyTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          

            
            JobfiyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
             JobifyScreen()
                }
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun JobfiyPreview() {
    JobfiyApp()
}