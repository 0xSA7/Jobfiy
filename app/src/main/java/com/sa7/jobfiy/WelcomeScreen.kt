package com.sa7.jobfiy

import DropdownMenuComponentWithLabel
import FilePickerTextField
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class WelcomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobApplicationScreen()
        }
    }
}


@Composable
fun JobApplicationScreen() {
    val options = listOf(
        "Software Engineer",
        "Product Manager",
        "Data Scientist",
        "UX Designer",
        "Mobile App Developer",
        "DevOps Engineer",
        "System Administrator",
        "Quality Assurance Engineer",
        "Technical Support Specialist",
        "Database Administrator"
    )
    var JopTitle by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var resume by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Choose an option") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Jobfiy",
                style = TextStyle(
                    fontSize = 37.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF364fc7)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Welcome",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Please enter your information so we could know you better!",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Gray
                ),
                // modifier = Modifier.padding(horizontal = 16.dp) // Ensure proper spacing
            )
            Spacer(modifier = Modifier.height(16.dp))

            // First Name Text Field
            CustomTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = "First name",
                placeholder = "Enter Your First Name"
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Second Name Text Field
            CustomTextField(
                value = secondName,
                onValueChange = { secondName = it },
                label = "Second name",
                placeholder = "Enter Your Second Name"
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Phone Number Text Field
            CustomTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone number",
                placeholder = "0xxxxxxxxx",
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(8.dp))

            DropdownMenuComponentWithLabel(
                options = options,
                label = "Jop Title",
                dropdownLabel = "Jop Title", // Custom label for the dropdown
                onOptionSelected = { selectedOption ->
                    // Handle the selected option here
                    println("Selected option: $selectedOption")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = JopTitle,
                onValueChange = { JopTitle = it },
                label = "Job Title",
                placeholder = "Enter Your Job Title",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Resume Text Field

            FilePickerTextField(
                label = "Upload Resume",
                onFileSelected = { uri ->
                    // Handle the selected file URI
                    if (uri != null) {
                        println("Selected file URI: $uri")
                    } else {
                        println("No file selected")
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Details Text Field (multiline)
            CustomTextField(
                value = details,
                onValueChange = { details = it },
                label = "Details",
                placeholder = "Add notes, if you want",
                isSingleLine = false
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Start Searching Button
            Button(
                onClick = { /* Handle click */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF364fc7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .height(50.dp)
            ) {
                Text(
                    text = "Start Searching",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}