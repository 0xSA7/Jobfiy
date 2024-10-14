package com.sa7.jobfiy.authentication.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sa7.jobfiy.R
import com.sa7.jobfiy.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(labelValue: String, icon: Painter, onTextSelected: (String) -> Unit,
                       errorStatus: Boolean,
                       errorMessage: String? = null) {
    val textValue = remember { mutableStateOf("") }
    Column (modifier = Modifier.padding(vertical = 4.dp)){
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                // Call the onTextSelected lambda function
                onTextSelected(it)
            },
            label = { Text(text = labelValue, style = MaterialTheme.typography.bodyMedium) },
            keyboardOptions = KeyboardOptions(
                // Change the imeAction to Next
                imeAction = ImeAction.Next
            ),
            maxLines = 1,
            // Set the error status
            isError = errorStatus,
            // Add leading and trailing icons
            leadingIcon = {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = PurpleGrey40
                )
            },
            trailingIcon = {
                // Display the error icon if errorStatus is true
                if (errorStatus)
                    Icon(
                        Icons.Filled.Warning,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
            },
        )
        // Display the error message if errorStatus is true and errorMessage is not null
        if (errorStatus && errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    isDone: Boolean = true,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean,
    errorMessage: String? = null
) {
    val passwordTextValue = remember { mutableStateOf("") }
    // Password visibility icon state
    val passwordVisibility = remember { mutableStateOf(false) }
    // Focus manager to clear focus when done is pressed
    val localFocusManager = LocalFocusManager.current
    Column (modifier = Modifier.padding(vertical = 4.dp)){
        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = passwordTextValue.value,
            onValueChange = {
                passwordTextValue.value = it
                // Call the onTextSelected lambda function
                onTextSelected(it)
            },
            label = { Text(text = labelValue, style = MaterialTheme.typography.bodyMedium) },
            // display keyboard with password type
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                // Change the imeAction to Done if isDone is true else Next
                imeAction =
                if (isDone) ImeAction.Done else
                    ImeAction.Next
            ),
            // Clear focus when done is pressed
            keyboardActions = if (isDone) {
                KeyboardActions {
                    localFocusManager.clearFocus()
                }
            } else {
                KeyboardActions.Default
            },
            singleLine = true,
            maxLines = 1,
            // Set the visual transformation based on the passwordVisibility state
            isError = errorStatus,
            // Add leading and trailing icons
            trailingIcon = {
                // Set the icon based on the passwordVisibility state
                val iconImage = if (passwordVisibility.value) {
                    R.drawable.visibility_asset
                } else {
                    R.drawable.visibility_off_asset
                }
                // Add an IconButton to toggle the password visibility
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        painter = painterResource(id = iconImage),
                        contentDescription = null,
                        tint = PurpleGrey40
                    )
                }
            },
            // Set the visual transformation based on the passwordVisibility state
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        // Display the error message if errorStatus is true and errorMessage is not null
        if (errorStatus && errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}

