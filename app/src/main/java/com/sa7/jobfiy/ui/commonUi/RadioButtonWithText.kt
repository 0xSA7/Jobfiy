package com.sa7.jobfiy.ui.commonUi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonWithText(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview() {
    var selectedOption by remember { mutableStateOf("Option 1") }

    Column {
        RadioButtonWithText(
            text = "Option 1",
            selected = selectedOption == "Option 1",
            onSelect = { selectedOption = "Option 1" }
        )
        RadioButtonWithText(
            text = "Option 2",
            selected = selectedOption == "Option 2",
            onSelect = { selectedOption = "Option 2" }
        )
    }
}
