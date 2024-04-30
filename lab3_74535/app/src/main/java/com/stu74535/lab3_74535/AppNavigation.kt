package com.stu74535.lab3_74535

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.stu74535.lab3_74535.Model.CartItem
import com.stu74535.lab3_74535.Model.OrderProduct
import com.stu74535.lab3_74535.Model.ProductItem
import com.stu74535.lab3_74535.View.Basket
import com.stu74535.lab3_74535.View.Catalog
import com.stu74535.lab3_74535.View.Login
import com.stu74535.lab3_74535.View.OrderHistory
import com.stu74535.lab3_74535.View.ProductScreen
import com.stu74535.lab3_74535.View.SignUp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(products:List<ProductItem>, currentCart:MutableList<OrderProduct>, categories:List<String>,carts:List<CartItem>) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val start : String = if (auth.currentUser != null)
    {
        Routes.Catalog.route
    }
    else
    {
        Routes.SignUp.route
    }
    NavHost(
        navController = navController,
        startDestination = start
    ) {
        composable(
            route = Routes.ProductScreen.route + "/{id}",
            arguments = listOf( navArgument(name = "id"){
                type = NavType.IntType
                defaultValue = 0
            }))
        { backStackEntry ->
            val product = backStackEntry.arguments
            if (product != null)
            {
                ProductScreen(
                    modifier = Modifier,
                    navController = navController,
                    productid = product.getInt("id"),
                    products = products,
                    currentCart = currentCart)
            }

        }
        composable(
            route = Routes.SignUp.route,

        )
        {
            SignUp(modifier = Modifier, navController = navController)
        }
        composable(route = Routes.Login.route)
        {
            Login(modifier = Modifier, navController = navController)
        }
        composable(route = Routes.Catalog.route)
        {
            Catalog(modifier = Modifier, navController = navController, categories = categories, products = products)
        }
        composable(route = Routes.Basket.route)
        {
            Basket(modifier = Modifier, navController = navController, currentCart = currentCart, products = products)
        }
        composable(route = Routes.OrderHistory.route)
        {
            OrderHistory(modifier = Modifier, navController = navController, products = products, carts = carts)
        }
    }
}
