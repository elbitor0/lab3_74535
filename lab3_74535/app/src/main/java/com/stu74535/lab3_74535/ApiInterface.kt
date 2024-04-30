package com.stu74535.lab3_74535

import com.stu74535.lab3_74535.Model.CartItem
import com.stu74535.lab3_74535.Model.ProductItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("products")
    fun getProduct(): Call<List<ProductItem>>

    @GET("product/{Id}")
    fun getProductId(@Path("Id") Id:Int): Call<ProductItem>

    @GET("carts/user/{Id}")
    fun getCarts(@Path("Id") Id:Int) : Call<List<CartItem>>

    @GET("products/categories")
    fun getCategories(): Call<List<String>>
    @POST("carts")
    fun addCarts(@Body cartItem: CartItem): Call<CartItem>
}