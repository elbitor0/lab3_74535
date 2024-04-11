package com.stu74535.lab2_74535

sealed class Routes(val route: String) {
    object HomePage : Routes("HomePage")
    object MovieScreen : Routes("MovieScreen")
    object BasketScreen: Routes("BasketScreen")
}