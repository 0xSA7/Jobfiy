@file:Suppress("DEPRECATION")

package com.sa7.jobfiy.authentication.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sa7.jobfiy.ui.theme.PurpleGrey40



//@Composable
//fun ShowToast(success: Boolean, message: String) {
//    val context = LocalContext.current
//    LaunchedEffect(key1 = success) { // Trigger recomposition when success changes
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
//}




@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = PurpleGrey40,
            thickness = 1.dp
        )

        Text(
            text = "OR",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            color = PurpleGrey40,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = PurpleGrey40,
            thickness = 1.dp
        )
    }

}






