package com.sa7.jobfiy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sa7.jobfiy.R
import com.sa7.jobfiy.ui.component.ButtonComponent
import com.sa7.jobfiy.ui.component.ClickableTextComponent
import com.sa7.jobfiy.ui.component.DividerTextComponent
import com.sa7.jobfiy.ui.component.GoogleButtonComponent
import com.sa7.jobfiy.ui.component.HeadingTextComponent
import com.sa7.jobfiy.ui.component.PasswordTextFieldComponent
import com.sa7.jobfiy.ui.component.TextFieldComponent
import com.sa7.jobfiy.ui.component.NormalTextComponent

@Composable
fun SignUpScreen(navController: NavController) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            NormalTextComponent("Hey there,")
            HeadingTextComponent("Create an account")

            TextFieldComponent("First Name", painterResource(id = R.drawable.person_asset))
            TextFieldComponent("Last Name",painterResource(id = R.drawable.person_asset))
            TextFieldComponent("Email",painterResource(id = R.drawable.email_asset))
            PasswordTextFieldComponent("Password")
            PasswordTextFieldComponent("Confirm Password")
            Spacer(modifier = Modifier.heightIn(80.dp))
            ButtonComponent("Sign Up")
            Spacer(modifier = Modifier.heightIn(10.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.heightIn(10.dp))
            GoogleButtonComponent()
            ClickableTextComponent("Already have an account? Sign In") {
                navController.navigate("login")
            }
        }
    }
}
