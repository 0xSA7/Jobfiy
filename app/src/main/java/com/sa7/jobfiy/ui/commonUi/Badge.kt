package com.sa7.jobfiy.ui.commonUi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Badge(text: String) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 4.dp, horizontal = 12.dp)
    ) {
        Text(text = text, color = Color.Gray)
    }
}