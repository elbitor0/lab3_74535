package com.stu74535.lab2_74535

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(modifier: Modifier, movieArray: SnapshotStateList<Movie>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomePage.route) {
        composable(route = Routes.MovieScreen.route + "/{Id}", arguments = listOf(
                navArgument(name = "Id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            ))
        { backstackEntry ->
            val arguments = backstackEntry.arguments
            if (arguments != null)
                MovieScreen(modifier, navController = navController,movieArray,arguments.getInt("Id") )

        }
        composable(route = Routes.HomePage.route)
        {
            HomePage(modifier, navController = navController, movieArray)
        }
        composable(route = Routes.BasketScreen.route)
        {
            BasketScreen(modifier, navController = navController,movieArray)
        }
    }
}