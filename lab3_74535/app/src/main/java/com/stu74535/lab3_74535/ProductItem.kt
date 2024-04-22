package com.stu74535.lab3_74535

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

data class ProductItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
{
    @Composable
    fun Image(modifier: Modifier)
    {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = modifier
        )
    }
}

