package com.sa7.jobfiy.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sa7.jobfiy.ui.navigation.AppNavGraph


@Composable
fun JobfiyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        val navController = rememberNavController()
        AppNavGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun JobfiyPreview() {
    JobfiyApp()
}