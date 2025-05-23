package com.example.orderterminal.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.AppTopBar
import com.example.orderterminal.navgation.Route

@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        topBar = { AppTopBar() },
        // BottomBarのホームアイコンを非活性にするため個別定義
        bottomBar = {
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
                        enabled = false
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
    ) { innerPadding ->
        MenuBody(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}


@Composable
fun MenuBody(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 24.dp,
                vertical = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Route.OrderDate)
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
                navController.navigate(Route.TryOut)
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
fun MenuPreview() {
    val navController = rememberNavController()
    MenuScreen(navController)
}