package com.sa7.jobfiy.ui.CommonUi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
)  {
object Home :BottomBarScreen(
    route = "home",
    title = "Home",
    icon = Icons.Default.Home
)object Profile :BottomBarScreen(
    route = "profile",
    title = "Profile",
    icon = Icons.Default.Person
)object Saved :BottomBarScreen(
    route = "saved",
    title = "Saved",
    icon = Icons.Default.MailOutline
)
}