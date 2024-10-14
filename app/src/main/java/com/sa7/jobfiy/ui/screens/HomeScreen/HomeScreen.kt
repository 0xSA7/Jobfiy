@file:OptIn(ExperimentalMaterial3Api::class)

import android.annotation.SuppressLint
import android.util.Log
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sa7.jobfiy.Database.Job
import com.sa7.jobfiy.R
import com.sa7.jobfiy.ui.commonUi.JobCard
import com.sa7.jobfiy.ui.commonUi.JobifyAppBar
import com.sa7.jobfiy.ui.commonUi.RadioButtonWithText
import com.sa7.jobfiy.ui.screens.JobSavedScreen.JobViewModel
import com.sa7.jobfiy.ui.screens.HomeScreen.HomeScreenViewModel
import com.sa7.jobfiy.ui.theme.Perpi


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobifyScreen(viewModel: HomeScreenViewModel, onSearchChange: (String) -> Unit, onJobClick: (String) -> Unit) {

fun JobifyScreen(navController: NavController) {

    var isSheetVisible by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val jobs = viewModel.data.observeAsState().value?.hits
    Log.d("JobifyScreen", "Jobs: $jobs")

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            JobifyAppBar()
            WelcomeSection(userName = "Khaled", screenWidth = screenWidth)

            SearchBar(screenWidth = screenWidth){ search ->
                onSearchChange(search)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.05f, vertical = 8.dp)
            ) {
                Text(
                    text = "Jobs based on your Search:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp // Adjusted font size
                )
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { isSheetVisible = !isSheetVisible },
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter",
                        tint = Perpi,
                        modifier = Modifier.size(24.dp) // Set consistent icon size
                    )
                }
            }


            IndeterminateCircularIndicator(viewModel)

            if (jobs.isNullOrEmpty()) {
                if (viewModel.isDataLoaded)
                    Text(text = "No jobs available", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(jobs) { job ->
                        Log.d("JobifyScreen", "Job: $job")
                        JobCard(job){
                            onJobClick(it)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = screenWidth * 0.05f),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(15) {
                    JobCard(navController)
                    Spacer(modifier = Modifier.height(12.dp))

                }
            }
            if (isSheetVisible) {
                ModalBottomSheet(
                    onDismissRequest = { isSheetVisible = false },
                    sheetState = sheetState
                ) {
                    BottomMenuContent()
                }
            }
        }
    }
}

@Composable
fun BottomMenuContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "Filter:",
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Workplace",
            modifier = Modifier.padding(4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        var selectedOption by remember { mutableStateOf("Remote") }

        Column {
            RadioButtonWithText(
                text = "Remote",
                selected = selectedOption == "Remote",
                onSelect = { selectedOption = "Remote" }
            )
            RadioButtonWithText(
                text = "On-site",
                selected = selectedOption == "On-site",
                onSelect = { selectedOption = "On-site" }
            )
            RadioButtonWithText(
                text = "Hybrid",
                selected = selectedOption == "Hybrid",
                onSelect = { selectedOption = "Hybrid" }
            )
        }

        HorizontalDivider(
            color = Perpi,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            "Country:",
            modifier = Modifier.padding(4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.padding(8.dp))

        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search for a country") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp) // Set consistent icon size
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        HorizontalDivider(
            color = Perpi,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        var textC by remember { mutableStateOf("") }

        Text(
            "City:",
            modifier = Modifier.padding(4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = textC,
            onValueChange = { textC = it },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search for a city") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp) // Set consistent icon size
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        HorizontalDivider(
            color = Perpi,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        ElevatedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Perpi,
                contentColor = Color.White
            )
        ) {
            Text("Filter")
        }


    }
}


@Composable
fun WelcomeSection(userName: String, screenWidth: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidth * 0.05f, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Welcome, $userName!",
                    color = Perpi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Let’s search for your job",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(screenWidth: Dp, onSearchChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(screenWidth * 0.05f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.isEmpty())
                    onSearchChange(it)
                text = it
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.weight(0.75F),
            placeholder = { Text(text = "Search a job") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp) // Set consistent icon size
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors()
        )

        IconButton(
            onClick = { onSearchChange(text) },
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Perpi,
                modifier = Modifier.size(24.dp) // Set consistent icon size
            )
        }
    }
}


@Composable
fun IndeterminateCircularIndicator(viewModel: HomeScreenViewModel) {
    if (!viewModel.isLoading) return

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(32.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = Perpi,
        )
    }

}


