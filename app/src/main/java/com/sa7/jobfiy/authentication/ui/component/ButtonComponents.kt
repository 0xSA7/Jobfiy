package com.sa7.jobfiy.authentication.ui.component

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sa7.jobfiy.R
import com.sa7.jobfiy.authentication.ui.screens.Login.AuthViewModel
import com.sa7.jobfiy.ui.theme.Purple40
import com.sa7.jobfiy.ui.theme.PurpleGrey40

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
                    // Create a gradient background
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
fun GoogleButtonComponent(viewModel: AuthViewModel = viewModel()) {

    // Create a launcher for Google Sign-In
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            // Pass the result to ViewModel to handle Google Sign-In
            viewModel.handleGoogleSignInResult(
                data = result.data,
                onSuccess = { user ->
                    // Handle success (e.g., navigate to another screen)
                    Log.d("SignIn", "User signed in: ${user.currentUser?.email}")
                },
                onFailure = { exception ->
                    // Handle failure (e.g., show error message)
                    Log.e("SignIn", "Sign-in failed", exception)
                }
            )
        }
    )

    Button(
        onClick = {
            // handle google login
            val signInIntent = viewModel.getGoogleSignInIntent()
            launcher.launch(signInIntent)
        },
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
                    // Create a gradient background
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

