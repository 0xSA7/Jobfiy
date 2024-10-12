package com.sa7.jobfiy
import JobifyScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.sa7.jobfiy.ui.commonUi.JobCard
import com.sa7.jobfiy.ui.screens.HomeScreenViewModel
import com.sa7.jobfiy.ui.theme.JobfiyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: HomeScreenViewModel =
            ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        viewModel.getJobsForCard()

        setContent {
            JobfiyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JobifyScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobfiyTheme {
        Greeting("Android")
    }
}