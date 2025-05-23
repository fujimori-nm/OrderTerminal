package com.example.orderterminal.ui.tryout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.AppBottomBar
import com.example.orderterminal.AppTopBar

import com.example.orderterminal.db.Item
import com.example.orderterminal.db.RoomApplication
import com.example.orderterminal.navgation.Route
import kotlinx.coroutines.launch

// 検証用の画面
@Composable
fun TryOutScreen(
    navController: NavController,
    app: RoomApplication
) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) },
    ) { innerPadding ->
        TryOutBody(
            navController = navController,
            app = app,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

// DBに1レコード格納する検証
@Composable
fun TryOutBody(
    navController: NavController,
    app: RoomApplication,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
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
        // ボタンを押すと1レコード登録される仮実装
        val coroutineScope = rememberCoroutineScope()
        val db = app.container.itemRepository
        val item = Item(
            code = "123457",
            name = "テスト商品",
            notes = "テスト規格",
            sale = 33,
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                coroutineScope.launch {
                    db.insertItem(item)
                }
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "DB登録", fontSize = 24.sp)
        }
        // 仮実装END
    }

}

// DB操作があるとPreviewは描画されない
@Preview(showBackground = true)
@Composable
fun TryOutPreview() {
    val navController = rememberNavController()
    val app: RoomApplication = RoomApplication()
    TryOutScreen(navController, app)
}