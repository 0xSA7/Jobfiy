package com.sa7.jobfiy.authentication.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sa7.jobfiy.authentication.ui.screens.Login.LoginViewModel
import com.sa7.jobfiy.ui.theme.Purple40


@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 36.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Purple40,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Purple40,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ClickableTextComponent(value: String, onClickButton: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ClickableText(
            // AnnotatedString is used to add multiple styles to the text
            text = AnnotatedString(value),
            onClick = { onClickButton() },
            modifier = Modifier
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 16.sp,
                color = Purple40,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun UnderlinedTextComponent(value: String, viewModel: LoginViewModel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ClickableText(
            text = AnnotatedString(value),
            onClick = {
                // Navigate to the ResetPassword screen
            },
            modifier = Modifier
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 16.sp,
                color = Purple40,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}