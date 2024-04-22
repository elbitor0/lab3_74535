package com.stu74535.lab3_74535

sealed class Routes(val route:String) {
    object Basket: Routes("basket")

    object Login: Routes("login")

    object SignUp: Routes("signup")

    object Catalog: Routes("catalog")

    object OrderHistory: Routes("orderhistory")

    object ProductScreen: Routes("productscreen")
}