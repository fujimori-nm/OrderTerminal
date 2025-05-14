package com.example.orderterminal

import android.R.attr.onClick
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.db.RoomApplication
import kotlinx.serialization.Serializable
import java.time.LocalDate

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
            Text(text = "発注（納品日設定）", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}, contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "発注一覧（納品日設定）", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Route.Greeting)
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "他メニュー１", fontSize = 24.sp)
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
                    動作検証用文字列 $presses times.
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

//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Icon(
                        Icons.Filled.Home,
                        modifier = Modifier.size(48.dp),
                        contentDescription = "Home"
                    )
                    Icon(
                        Icons.Filled.Settings,
                        modifier = Modifier.size(48.dp),
                        contentDescription = "Settings"
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
//        content = { innerPadding -> OrderMenu(presses) }
        content = { innerPadding -> AppNavHost(innerPadding, presses) }
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
    @Serializable
    data object Greeting
}

@Composable
fun AppNavHost(innerPadding: PaddingValues, presses: Int) {
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
            OrderDateSetting(
                navController
            )
        }
        composable<Route.Greeting> {
            Greeting(
                navController
            )
        }
    }
}
// 便指定の定義
enum class Trucking(val labelText: String) {
    Unspecified("指定なし"), First("1便"), Second("2便"), Third("3便")
}


@Composable
fun OrderDateSetting(navController: NavController) {
    var orderDate: String by remember { mutableStateOf(LocalDate.now().toString()) }
    var showDateDialog: Boolean by remember { mutableStateOf(false) }

    var selectedFruit: Trucking by remember {
        mutableStateOf(Trucking.Unspecified)
    }

    Column(
        modifier = Modifier.padding(
            horizontal = 24.dp,
            vertical = 80.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Box {
            OutlinedTextField(
                value = orderDate,
                onValueChange = { orderDate = it },
                label = { Text("発注日") }
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable {
                        Log.v("TAG", LocalDate.now().toString())
                        showDateDialog = true
                    }
            )
        }
        if(showDateDialog) {
            DatePickerModal(
                onDateSelected = {},
                onDismiss = {showDateDialog = false}
            )
        }
        Column {
            Text(
                text = "配送便指定",
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 0.dp),
                fontSize = 15.sp
            )

            Row {
                Trucking.entries.forEach {
                    FilterChip(
                        modifier = Modifier.padding(8.dp),
                        selected = it == selectedFruit,
                        onClick = { selectedFruit = it },
                        label = { Text(text = it.labelText) },
                        leadingIcon = {
                            if (it == selectedFruit) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        }
        Text("")
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Route.Menu) },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "納品日設定へ", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Route.Menu) },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "戻る", fontSize = 24.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DatePickerModalPreview() {
//    DatePickerModal(
//        onDateSelected = {},
//        onDismiss = {}
//    )
//}


@Preview(showBackground = true)
@Composable
fun OrderDateSettingPreview() {
    val navController = rememberNavController()
    OrderDateSetting(navController)
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
