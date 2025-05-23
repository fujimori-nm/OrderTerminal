package com.example.orderterminal.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orderterminal.db.RoomApplication
import com.example.orderterminal.ui.deliverydate.DeliveryDateScreen
import com.example.orderterminal.ui.menu.MenuScreen
import com.example.orderterminal.ui.orderdate.OrderDateScreen
import com.example.orderterminal.ui.tryout.TryOutScreen
import kotlinx.serialization.Serializable

// navigation用のRoute定義
object Route {
    @Serializable
    data object Menu

    @Serializable
    data object OrderDate

    @Serializable
    data object DeliveryDate

    @Serializable
    data object TryOut
}

@Composable
fun AppNavHost(
    app: RoomApplication,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Menu
    ) {
        composable<Route.Menu> {
            MenuScreen(
                navController,
            )
        }
        composable<Route.OrderDate> {
            OrderDateScreen(
                navController,
            )
        }
        composable<Route.DeliveryDate> {
            DeliveryDateScreen(
                navController
            )
        }
        composable<Route.TryOut> {
            TryOutScreen(
                navController,
                app
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavHost2Preview() {
    val app: RoomApplication = RoomApplication()
    AppNavHost(app = app)
}