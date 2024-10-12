package com.sa7.jobfiy.ui.profile

import JobifyAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sa7.jobfiy.R



@Composable
fun JobfiyProfileScreen() {
    var isDarkMode by remember { mutableStateOf(false) }
    val navController = rememberNavController()


    Scaffold(

//        bottomBar = {
//            BottomNavigationBar(navController = navController)}
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding) // Apply the content padding from Scaffold
                .padding(16.dp)
        ) {
            JobifyAppBar()

            Spacer(modifier = Modifier.height(16.dp))

            // Profile Section
            Text("Welcome, Mohammed!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileItem(icon = Icons.Default.Email, text = "Example@gmail.com")
            ProfileItem(icon = Icons.Default.Phone, text = "01xxxxxxxx")
            ProfileItem(icon = Icons.Default.LocationOn, text = "Cairo, EG")

            Spacer(modifier = Modifier.height(16.dp))

            // Resume Section
            Text("Resumes", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            ResumeItem()

            Spacer(modifier = Modifier.height(16.dp))

            // Increase Job Matches Section
            Text("Increase your job matches", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            ProfileItem(icon = Icons.Default.Notifications, text = "Qualifications")
            ProfileItem(icon = Icons.Default.Settings, text = "Job Preference")

            Spacer(modifier = Modifier.height(16.dp))

            // Dark Mode Toggle
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Mode", fontSize = 16.sp)
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it },
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Log out button
            TextButton(onClick = { /* Handle logout */ }) {
                Text("Log out", color = Color.Red)
            }
        }
    }
}



@Composable
fun ProfileItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun ResumeItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Jobfiy Resume", fontSize = 16.sp)
            Text("Updated Sep 21, 2024", fontSize = 14.sp, color = Color.Gray)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewJobfiyProfileScreen() {
    JobfiyProfileScreen()
}
