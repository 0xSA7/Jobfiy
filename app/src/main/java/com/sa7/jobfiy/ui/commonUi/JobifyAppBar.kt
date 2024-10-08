package com.sa7.jobfiy.ui.commonUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sa7.jobfiy.R
import com.sa7.jobfiy.ui.theme.Perpi

@Composable
fun JobifyAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.jobfiy),
            contentDescription = "User Image"
        )

        Row {
            IconButton(
                onClick = { },
                colors = IconButtonDefaults.iconButtonColors(contentColor = Perpi)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp) // Set consistent icon size
                )
            }
            IconButton(
                onClick = { },
                colors = IconButtonDefaults.iconButtonColors(contentColor = Perpi)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings",
                    modifier = Modifier.size(24.dp) // Set consistent icon size
                )
            }
        }
    }
}