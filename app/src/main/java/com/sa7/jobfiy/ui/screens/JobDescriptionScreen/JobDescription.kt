
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.HtmlCompat
import com.sa7.jobfiy.ui.commonUi.Badge
import com.sa7.jobfiy.ui.commonUi.JobifyAppBar
import com.sa7.jobfiy.ui.theme.Perpi
import com.google.accompanist.flowlayout.FlowRow
import com.sa7.jobfiy.ui.commonUi.InfoBox
import com.sa7.jobfiy.ui.commonUi.JobDetailItem
import com.sa7.jobfiy.ui.screens.JobDescriptionScreen.JobDescriptionViewModel

@Composable
fun JobDetailPage(viewModel: JobDescriptionViewModel) {

    val job = viewModel.data.observeAsState().value

    // Retrieve screen dimensions
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Adjust the padding according to screen width
    val horizontalPadding = if (screenWidth < 360.dp) 8.dp else 16.dp
    val verticalPadding = if (screenHeight < 600.dp) 8.dp else 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        // Top App Bar
        JobifyAppBar()

        Spacer(modifier = Modifier.height(16.dp))

        // Job Title Section
        Text(
            text = job!!.job_title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(modifier = Modifier.padding(top = 8.dp)) {
            Badge(text = job!!.job_type)
            Spacer(modifier = Modifier.width(8.dp))
            //  Badge(text = "Full Time")
        }

        Text(
            text = "${job?.company?.name} - ${job?.location}",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Applicant Information Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoBox("40", "Applicants", Modifier.weight(1f))
            InfoBox("30", "Viewed", Modifier.weight(1f))
            InfoBox("0", "In Consideration", Modifier.weight(1f))
            InfoBox("0", "Not Selected", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current
        // Apply Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job!!.indeed_final_url))
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Perpi)
        ) {
            Text(text = "Apply", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Job Details Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Text("Job details:", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            JobDetailItem(label = "Experience Needed:", value = "3 to 5 years")
            JobDetailItem(
                label = "Career Level:",
                value = "Entry Level (Junior Level / Fresh Grad)"
            )
            JobDetailItem(label = "Education Level:", value = "Bachelor's Degree")
            JobDetailItem(
                label = "Salary:",
                value = "${job!!.salary.min} to ${job!!.salary.max} Per ${job!!.salary.type}"
            )
            JobDetailItem(label = "Job Categories:", value = "IT/Software Development")

            Spacer(modifier = Modifier.height(24.dp))

            // Skills and Tools Section
            Text("Skills and Tools", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            // FlowRow layout for skills
            FlowRow(
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Badge("Software Testing")
                Badge("Automation Testing")
                Badge("Regression Testing")
                Badge("Quality Assurance")
                Badge("Unit Testing")
                Badge("Agile")
                Badge("Agile Testing")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Job Description Section
            Text("Job description", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(
                text = HtmlCompat.fromHtml(job!!.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()
                    .trimIndent(),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}



