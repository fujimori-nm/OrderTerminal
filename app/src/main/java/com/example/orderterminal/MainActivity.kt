package com.example.orderterminal

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.db.RoomApplication
import com.example.orderterminal.ui.theme.OrderTerminalTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private val dao = RoomApplication.database.itemDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            OrderMenu()
            ScaffoldExample()
        }
        Log.v("TAG", "Hello, world!")
    }
}

@Composable
fun OrderMenu(
    navController: NavController,
    presses: Int,
) {
//    var presses by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("Result") }

    Column(
//            modifier = Modifier.padding(innerPadding),
        modifier = Modifier.padding(
            horizontal = 24.dp,
            vertical = 80.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Route.SecondPage)
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "発注日設定", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}, contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "納品日指定発注", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}, contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "納品日指定発注一覧", fontSize = 24.sp)
        }
        HorizontalDivider(thickness = 2.dp)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}, contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "発注データ送信", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showDialog = true },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "マスターデータ受信", fontSize = 24.sp)
        }

        Text(
            modifier = Modifier.padding(8.dp),
            text =
                """
                    You have pressed the floating action button $presses times.
                """.trimIndent(),
        )
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                result = "Dismiss"
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        result = "OK"
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        result = "Cancel"
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Text("AlertDialog")
            },
            text = {
                Text("これはJetpack Composeのダイアログです。Material3デザインに対応しています。")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderMenuPreview() {
    val navController = rememberNavController()
    OrderMenu(navController, presses = 99)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("商品管理アプリ")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
//        content = { innerPadding -> OrderMenu(presses) }
        content = { innerPadding -> AppNavHost(presses) }
    )
}

@Preview(showBackground = true)
@Composable
fun ScaffoldExamplePreview() {
    ScaffoldExample()
}

object Route {
    @Serializable
    data object Menu

    @Serializable
    data object SecondPage
//    data class SecondPage (
//        val name: String
//    )
}

@Composable
fun AppNavHost(presses: Int,) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Menu
    ) {
        composable<Route.Menu> {
            OrderMenu(
                navController,
                presses
            )
        }
        composable<Route.SecondPage> {
            Greeting(
                navController
            )
        }
    }
}

@Composable
fun Greeting(navController: NavController) {
    Column(
        modifier = Modifier.padding(
            horizontal = 24.dp,
            vertical = 80.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text("あいうえお")
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Route.Menu) },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "戻る", fontSize = 24.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    Greeting(navController)
}

