package com.example.orderterminal.ui.deliverydate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.AppBottomBar
import com.example.orderterminal.AppTopBar
import com.example.orderterminal.TableCell
import com.example.orderterminal.TableRowTitleCell
import java.time.LocalDate


@Composable
fun DeliveryDateScreen(navController: NavController) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) },
        // メインコンテンツ
    ) { innerPadding ->
        DeliveryDateBody(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}


@Composable
fun DeliveryDateBody(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var code: String by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(
                horizontal = 24.dp,
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            Text(
                text = "発注日：2025-02-20",
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "便指定：指定なし",
                color = MaterialTheme.colorScheme.primary,
            )
        }
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
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row {
                Text(
                    text = "名称",
                    modifier = Modifier.width(50.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text("はごろも　シーチキンL")
            }
            Row {
                Text(
                    text = "規格",
                    modifier = Modifier.width(50.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text("１４０ｇ")
            }
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "在庫数",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "13.0",
                    fontSize = 16.sp,
                )
            }
            Row {
                Text(
                    text = "売価",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "408",
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,

                    )
                Text(
                    text = "原価",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "296.0",
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,
                )
            }
            Row {
                Text(
                    text = "発注単位",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "5",
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,

                    )
                Text(
                    text = "入数",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "120",
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,
                )
            }
            Row {
                Text(
                    text = "平日日販",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "8",
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,

                    )
                Text(
                    text = "土日日販",
                    modifier = Modifier.width(60.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "10",
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,
                )
            }
        }
        //ここから表
        Text("納品数設定表")
        val colWidth = 40.dp
        val day: Int = LocalDate.now().dayOfMonth
        val days: List<Int> = (day + 1..day + 7).toList()
        val daysAsStr: List<String> = days.map { it.toString() }
        var dayTitles: MutableList<String> = mutableListOf("")
        val rowTitles: List<String> = listOf("1便", "2便", "3便", "売", "性")
        dayTitles.addAll(daysAsStr)
        Column {
            Row(
                modifier = Modifier.background(Color(0xFFCCC2DC))
            ) {
                for (title in dayTitles) {
                    TableCell(text = title, width = colWidth)
                }
            }
            for (rowTitle in rowTitles) {
                Row {
                    TableRowTitleCell(text = rowTitle, width = colWidth)
                    repeat(7) {
                        TableCell(text = "999", width = colWidth)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "納品数確定", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {},
            contentPadding = PaddingValues(16.dp, 8.dp)
        ) {
            Text(text = "発注一覧", fontSize = 16.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryDatePreview() {
    val navController = rememberNavController()
    DeliveryDateScreen(navController)
}