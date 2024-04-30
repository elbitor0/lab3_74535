package com.stu74535.lab3_74535.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74535.lab3_74535.Add
import com.stu74535.lab3_74535.AddToCart
import com.stu74535.lab3_74535.Model.OrderProduct
import com.stu74535.lab3_74535.Model.ProductItem
import com.stu74535.lab3_74535.Remove
import kotlinx.coroutines.selects.select

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(modifier: Modifier, navController: NavController,productid:Int ,products: List<ProductItem>, currentCart : MutableList<OrderProduct>)
{
    val product = products.find { it.id == productid }
    requireNotNull(product) { "Product with id $productid not found" }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(product.title)
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

        ) {

        innerPadding ->
        var amount = remember {mutableStateOf(0)}

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
                product.Image(Modifier)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            item {
                Text(
                    text = "Category: ${product.category}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            item {
                Text(
                    text = "Description: ${product.description}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item{
                Row {
                    Add(amount = amount)
                    Text(text = "${amount}")
                    Remove(amount = amount)

                }

            }
            item {
                AddToCart(amount = amount, product = product, currentCart = currentCart)
            }

        }
    }


}