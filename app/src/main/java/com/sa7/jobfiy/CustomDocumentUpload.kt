import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilePickerTextField(
    label: String = "Select File",
    onFileSelected: (Uri?) -> Unit = {}
) {
    var selectedFilePath by remember { mutableStateOf("") }

    // File picker launcher using ActivityResultContracts
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val fileUri: Uri? = result.data?.data
            selectedFilePath = fileUri?.path ?: "No file selected"
            onFileSelected(fileUri)
        }
    }

    // Function to open file picker
    fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*" // File type (e.g., "image/*" for images)
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        filePickerLauncher.launch(intent)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Text field that opens the file picker when clicked
        OutlinedTextField(
            value = selectedFilePath,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { openFilePicker() },
            label = { Text(label) },
            enabled = false // Disable direct text input
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Optional button to manually open the file picker
        Button(onClick = { openFilePicker() }) {
            Text("Browse")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilePickerTextFieldPreview() {
    FilePickerTextField(label = "Upload File") {
        // This will get called when a file is selected
        println("File URI: $it")
    }
}
