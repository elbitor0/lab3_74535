package com.stu74535.lab3_74535.Model

import com.google.gson.annotations.SerializedName



data class CartItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("products")
    val products: List<OrderProduct>,
    @SerializedName("__v")
    val version: Int
)
data class OrderProduct(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)