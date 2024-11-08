package com.example.pr04

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pr04.ui.theme.PR04Theme
import java.nio.file.WatchEvent
import java.util.Collections.list

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PR04Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var selectedText: String by remember { mutableStateOf("") }
    var expanded: Boolean by remember { mutableStateOf(false) }
    val hobbies = listOf("Add", "Call", "Heart", "Like")

    var min by remember { mutableStateOf("") }
    var max by remember { mutableStateOf("") }

    var sliderValue: Float by remember { mutableStateOf(0f) }
    var finishValue: String by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 80.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .clickable { expanded = true }
                .fillMaxWidth(),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                // Afegir colors i detalls al desplegable:
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            hobbies.forEach { hobby ->
                DropdownMenuItem(
                    text = { Text(text = hobby) },
                    onClick = {
                        expanded = false
                        selectedText = hobby
                    }
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f) // Ajusta el espacio horizontal entre las columnas
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = "Min",
                    textAlign = TextAlign.Center
                )
                TextField(
                    value = min,
                    onValueChange = { min = it },
                    modifier = Modifier.size(80.dp, 30.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f) // Ajusta el espacio horizontal entre las columnas
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = "Max",
                    textAlign = TextAlign.Center
                )
                TextField(
                    value = max,
                    onValueChange = { max = it },
                    modifier = Modifier.size(80.dp, 30.dp)
                )
            }
        }
        Text(text = finishValue)
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            onValueChangeFinished = { finishValue = sliderValue.toString()},
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )
        Row () {
            Icon(
            Icons.Rounded.ShoppingCart,
            contentDescription = stringResource(id = R.string.shopping_cart_content_desc)
        ) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PR04Theme {
        Greeting("Android")
    }
}