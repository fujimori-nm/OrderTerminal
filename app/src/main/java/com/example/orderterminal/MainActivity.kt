package com.example.orderterminal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.orderterminal.ui.theme.OrderTerminalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            OrderTerminalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    TestButton(modifier = Modifier.padding(innerPadding))
                    Button(onClick = {}) { Text(text = "発注日設定", fontSize = 24.sp) }
                }
            }
        }
    }
}

@Composable
fun TestButton(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Button(onClick = {}) { Text(text = "発注日設定", fontSize = 24.sp) }
    }
}

@Preview(showBackground = true)
@Composable
fun TestButtonPreview() {
    OrderTerminalTheme {
        TestButton()
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
    OrderTerminalTheme {
        Greeting("Android")
    }
}