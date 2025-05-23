package com.example.orderterminal.ui.orderdate

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.AppBottomBar
import com.example.orderterminal.AppTopBar
import com.example.orderterminal.DatePickerModal
import com.example.orderterminal.Trucking
import com.example.orderterminal.navgation.Route
import com.example.orderterminal.ui.menu.MenuBody
import com.example.orderterminal.ui.menu.MenuScreen
import java.time.LocalDate

@Composable
fun OrderDateScreen(navController: NavController) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) },
        // メインコンテンツ
    ) { innerPadding ->
        OrderDateBody(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun OrderDateBody(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var orderDate: String by remember { mutableStateOf(LocalDate.now().toString()) }
    var showDateDialog: Boolean by remember { mutableStateOf(false) }

    var selectedFruit: Trucking by remember {
        mutableStateOf(Trucking.Unspecified)
    }

    Column(
        modifier = modifier
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
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Route.DeliveryDate) },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "納品数設定へ", fontSize = 24.sp)
        }
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
fun OrderDatePreview() {
    val navController = rememberNavController()
    OrderDateScreen(navController)
}