package com.sa7.jobfiy
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isSingleLine: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Label for the text field
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Text field container with background and border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp) // Padding around the text field (horizontal)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType
                ),
                singleLine = isSingleLine,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp), // Vertical padding inside the text field
                decorationBox = { innerTextField ->
                    // Show placeholder text if the field is empty
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}






