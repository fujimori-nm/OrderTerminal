package com.example.orderterminal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
            // メインコンテンツの呼出は、AppScaffold＞AppHostNav＞メインコンテンツ
            AppScaffold()
        }
        Log.v("アプリ", "onCreate End")
    }
}

@Composable
fun OrderMenu(
    innerPadding: PaddingValues,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(
                horizontal = 24.dp,
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Route.OrderDateSetting)
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "発注（納品日指定）", fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}, contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "発注一覧（納品日指定）", fontSize = 24.sp)
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
            onClick = {},
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "マスターデータ受信", fontSize = 24.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderMenuPreview() {
    val navController = rememberNavController()
    val innerPadding = PaddingValues(8.dp)
    OrderMenu(innerPadding, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
) {
    val navController = rememberNavController()
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
                ) {
                    IconButton(onClick = {
                        navController.navigate(Route.Menu)
                    },
//                        ToDo メニュー画面の時は非活性
//                        enabled = false
                    ) {
                        Icon(
                            Icons.Filled.Home,
                            modifier = Modifier.size(48.dp),
                            contentDescription = "Home"
                        )
                    }
                    Icon(
                        Icons.Filled.Settings,
                        modifier = Modifier.size(48.dp),
                        contentDescription = "Settings"
                    )
                }
            }
        },
//        floatingActionButton = {}
        // メインコンテンツ
    ) { innerPadding -> AppNavHost(innerPadding,navController= navController)}
}

@Preview(showBackground = true)
@Composable
fun AppScaffoldPreview() {
    AppScaffold()
}

object Route {
    @Serializable
    data object Menu

    @Serializable
    data object OrderDateSetting

    @Serializable
    data object OrderSetting

    @Serializable
    data object Greeting
}

@Composable
fun AppNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {
//    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Menu
    ) {
        composable<Route.Menu> {
            OrderMenu(
                innerPadding,
                navController,
            )
        }
        composable<Route.OrderDateSetting> {
            OrderDateSetting(
                innerPadding,
                navController,
            )
        }
        composable<Route.OrderSetting> {
            OrderSetting(
                innerPadding,
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

// 発注日、便指定画面
@Composable
fun OrderDateSetting(
    innerPadding: PaddingValues,
    navController: NavController,
) {
    var orderDate: String by remember { mutableStateOf(LocalDate.now().toString()) }
    var showDateDialog: Boolean by remember { mutableStateOf(false) }

    var selectedFruit: Trucking by remember {
        mutableStateOf(Trucking.Unspecified)
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(
                horizontal = 24.dp,
                vertical = 24.dp
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
        if (showDateDialog) {
            DatePickerModal(
                onDateSelected = {},
                onDismiss = { showDateDialog = false }
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
            onClick = {navController.navigate(Route.OrderSetting)},
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "納品数設定へ", fontSize = 24.sp)
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
    val innerPadding = PaddingValues(8.dp)

    OrderDateSetting(innerPadding, navController)
}


@Composable
fun OrderSetting(
    innerPadding: PaddingValues,
    navController: NavController,
) {
    var code: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(
                horizontal = 24.dp,
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("商品コード") },
            trailingIcon = {
                Icon(
                    Icons.Filled.QrCodeScanner,
                    modifier = Modifier.size(48.dp),
                    contentDescription = "Settings"
                    )
            }
        )
        Row{
            Text(
                text = "名称",
                modifier =  Modifier.width(50.dp)
            )
            Text("はごろも　シーチキンL")
        }
        Row{
            Text(
                text = "規格",
                modifier =  Modifier.width(50.dp)
            )
            Text("１４０ｇ")
        }
        Row(
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text="在庫数",
                modifier =  Modifier.width(60.dp)
            )
            Text(
                text = "13.0",
                fontSize = 16.sp,
            )
        }
        Row{
            Text(
                text="売価",
                modifier =  Modifier
                    .width(60.dp)
            )
            Text(
                text = "408",
                modifier =  Modifier.width(60.dp).wrapContentSize(Alignment.Center),
                fontSize = 16.sp,

            )
            Text(
                text="原価",
                modifier =  Modifier.width(60.dp)
            )
            Text(
                text="296.0",
                modifier =  Modifier.width(60.dp).wrapContentSize(Alignment.Center),
                fontSize = 16.sp,
            )
        }
        Row{
            Text(
                text="発注単位",
                modifier =  Modifier.width(60.dp)
            )
            Text(
                text = "5",
                modifier =  Modifier.width(60.dp).wrapContentSize(Alignment.Center),
                fontSize = 16.sp,

                )
            Text(
                text="入数",
                modifier =  Modifier.width(60.dp)
            )
            Text(
                text="120",
                modifier =  Modifier.width(60.dp).wrapContentSize(Alignment.Center),
                fontSize = 16.sp,
            )
        }
        Row{
            Text(
                text="平日日販",
                modifier =  Modifier.width(60.dp)
            )
            Text(
                text = "8",
                modifier =  Modifier.width(60.dp).wrapContentSize(Alignment.Center),
                fontSize = 16.sp,

                )
            Text(
                text="土日日販",
                modifier =  Modifier.width(60.dp)
            )
            Text(
                text="10",
                modifier =  Modifier.width(60.dp).wrapContentSize(Alignment.Center),
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSettingPreview() {
    val navController = rememberNavController()
    val innerPadding = PaddingValues(8.dp)

    OrderSetting(innerPadding, navController)
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

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    val navController = rememberNavController()
//    Greeting(navController)
//}
