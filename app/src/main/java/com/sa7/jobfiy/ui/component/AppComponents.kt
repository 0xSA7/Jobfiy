@file:Suppress("DEPRECATION")

package com.sa7.jobfiy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sa7.jobfiy.R
import com.sa7.jobfiy.ui.theme.Purple40
import com.sa7.jobfiy.ui.theme.PurpleGrey40

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
fun TextFieldComponent(labelValue: String, icon: Painter, onTextSelected: (String) -> Unit,
                       errorStatus: Boolean,
                       errorMessage: String? = null) {
    val textValue = remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.small),
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextSelected(it)
            },
            label = { Text(text = labelValue) },
            // display keyboard
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            maxLines = 1,
            isError = errorStatus,
            leadingIcon = {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = PurpleGrey40
                )
            },
            trailingIcon = {
                if (errorStatus)
                    Icon(
                        Icons.Filled.Warning,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
            },
        )
        if (errorStatus && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

    }


@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    isDone: Boolean = true,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean,
    errorMessage: String? = null
) {
    val passwordTextValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.small),
            value = passwordTextValue.value,
            onValueChange = {
                passwordTextValue.value = it
                onTextSelected(it)
            },
            label = { Text(text = labelValue) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction =
                if (isDone) ImeAction.Done else
                    ImeAction.Next
            ),
            keyboardActions = if (isDone) {
                KeyboardActions {
                    localFocusManager.clearFocus()
                }
            } else {
                KeyboardActions.Default
            },
            singleLine = true,
            maxLines = 1,
            isError = errorStatus,
            trailingIcon = {
                val iconImage = if (passwordVisibility.value) {
                    R.drawable.visibility_asset
                } else {
                    R.drawable.visibility_off_asset
                }
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        painter = painterResource(id = iconImage),
                        contentDescription = null,
                        tint = PurpleGrey40
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        if (errorStatus && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}

@Composable
fun ButtonComponent(value: String, onClickButton: () -> Unit) {
    Button(
        onClick = {
            onClickButton.invoke()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(), colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Purple40, PurpleGrey40)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
                color = Color.White
            )
        }
    }
}

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

@Composable
fun GoogleButtonComponent() {
    Button(
        onClick = { /* Handle Google Sign-In */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color(0xFF4285F4), Color(0xFF34A853))),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(34.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Continue with Google",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ClickableTextComponent(value: String, onClickButton: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ClickableText(
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
fun UnderlinedTextComponent(value: String, onClickButton: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ClickableText(
            text = AnnotatedString(value),
            onClick = { onClickButton() },
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


