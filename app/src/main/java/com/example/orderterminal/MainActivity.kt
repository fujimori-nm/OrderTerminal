package com.example.orderterminal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.orderterminal.db.RoomApplication
import com.example.orderterminal.navgation.AppNavHost
import com.example.orderterminal.navgation.Route

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val app = application as RoomApplication
            // 検証用のTryOutScreeでDB検証の為にappを渡している（削除予定）
            AppNavHost(app)
        }
        Log.v("アプリ", "onCreate End")
    }
}

// TopBarは共通の為ここに配置
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("商品管理アプリ")
        }
    )
}

// BottomBarはMenuScreen以外共通
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(navController: NavController) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Route.Menu)
                },
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
}


// 便指定の定義（OrderDateScreenに移植予定）
enum class Trucking(val labelText: String) {
    Unspecified("指定なし"), First("1便"), Second("2便"), Third("3便")
}

// 発注日の入力時に利用する日付入力ダイアログ
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


// 納品数入力表のセル
@Composable
fun RowScope.TableCell(
    text: String,
    width: Dp,
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .width(width)
            .padding(8.dp)
    )
}

// 納品数入力表の背景色があるセル
@Composable
fun RowScope.TableRowTitleCell(
    text: String,
    width: Dp,
) {
    Text(
        text = text,
        Modifier
            .background(Color(0xFFCCC2DC))
            .border(1.dp, Color.Black)
            .width(width)
            .padding(8.dp)
    )
}
