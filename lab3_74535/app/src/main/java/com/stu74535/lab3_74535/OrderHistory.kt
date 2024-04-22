package com.stu74535.lab3_74535

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistory(modifier: Modifier, navController: NavController, products:List<ProductItem>) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("History")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Screen 1 bottom app bar",
                )
            }
        },

        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        }
    }
}

@Composable
fun CartSection(modifier: Modifier, cart: CartItem, productItems: List<ProductItem>) {
    var totalPrice:Int =0
    LazyColumn {
        item { Text(text = cart.date) }
        item {
            for (product in cart.products) {
                val matchedProduct = productItems.find { it.id == product.productId }
                if (matchedProduct != null) {
                    totalPrice += product.quantity * product.quantity
                    Card(modifier = modifier, amount = product.quantity, product = matchedProduct)
                } else {
                    // Handle when product is not found
                }
            }
        }
    }
}
@Composable
fun Card(modifier: Modifier,amount :Int, product:ProductItem)
{
    Row(modifier) {
        product.Image(modifier = Modifier)
        Column {
            Text(text = product.title)
            Text(text = "Amount : ${amount}")
            Text(text = "Price : ${product.price*amount}")
        }
    }

}