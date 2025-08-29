package com.example.securetasks


import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.securetasks.data.SecureStorage
import com.example.securetasks.data.TaskRepository
import com.example.securetasks.model.Task


class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
// Prevent screenshots/recording (OWASP MASVS defense-in-depth)
window.setFlags(
WindowManager.LayoutParams.FLAG_SECURE,
WindowManager.LayoutParams.FLAG_SECURE
)


val repo = TaskRepository(SecureStorage(this))


setContent {
MaterialTheme {
SecureTasksScreen(repo)
}
}
}
}


@Composable
fun SecureTasksScreen(repo: TaskRepository) {
var title by remember { mutableStateOf(TextFieldValue("")) }
var notes by remember { mutableStateOf(TextFieldValue("")) }
var error by remember { mutableStateOf<String?>(null)
