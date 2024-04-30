package com.stu74535.lab3_74535.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74535.lab3_74535.Model.ProductItem
import com.stu74535.lab3_74535.R
import com.stu74535.lab3_74535.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalog(modifier: Modifier, navController: NavController, categories:List<String>, products:List<ProductItem>)
{
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Catalog")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {

            }
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyRow {
                items(categories) { category ->
                    Text(
                        text = category,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(products) { product ->
                    ProductItem(product = product,navController = navController)
                }

            }
        }
    }


}

@Composable
fun ProductItem(product: ProductItem, navController: NavController) {
    Button(onClick = { navController.navigate(Routes.ProductScreen.route + "/${product.id}") }) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .width(100.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.padlock), // Replace R.drawable.placeholder with your actual drawable resource or URL
                contentDescription ="Product Picture", // Add a description for accessibility
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
