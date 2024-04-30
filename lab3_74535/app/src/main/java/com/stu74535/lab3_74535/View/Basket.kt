package com.stu74535.lab3_74535.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74535.lab3_74535.Model.OrderProduct
import com.stu74535.lab3_74535.Model.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Basket(modifier: Modifier, navController: NavController, currentCart : MutableList<OrderProduct>, products: List<ProductItem>)
{
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Basket")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Screen 1 bottom app bar",
                )
            }
        },

        ) {

            innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(currentCart) { product ->
                val matchedProduct = products.find { it.id == product.productId }
                if (matchedProduct != null) {
                    ProductCard(modifier = Modifier,product = matchedProduct ,orderProduct = product)
                } else {
                    // Handle when product is not found
                }
            }
        }


    }
}

@Composable
fun ProductCard(modifier:Modifier, product: ProductItem, orderProduct: OrderProduct) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        product.Image(Modifier.size(50.dp))

        // Title
        Column(modifier) {
            Text(text = product.title)
            Text(text = "Amount: ${orderProduct.quantity}")
            Text(text = "Price: ${orderProduct.quantity*product.price}")
        }


    }
}