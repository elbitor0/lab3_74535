package com.stu74535.lab3_74535

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.stu74535.lab3_74535.Model.CartItem
import com.stu74535.lab3_74535.Model.OrderProduct
import com.stu74535.lab3_74535.Model.ProductItem
import com.stu74535.lab3_74535.ui.theme.Lab3_74535Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://fakestoreapi.com/"
class MainActivity : ComponentActivity() {
    var products : MutableList<ProductItem> = mutableListOf()
    var carts:List<CartItem> = listOf()
    var categories:List<String> = listOf()
    var currentCart : MutableList<OrderProduct> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProduct()
        getCategories()
        setContent {
            Lab3_74535Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(products =products, currentCart = currentCart, categories = categories, carts = carts)
                }
            }
        }

    }

    private fun getProduct() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getProduct()

        retrofitData.enqueue(object : Callback<List<ProductItem>?> {
            override fun onResponse(
                p0: Call<List<ProductItem>?>,
                p1: Response<List<ProductItem>?>
            ) {
                val responseBody = p1.body()!!
                products.addAll( responseBody)

            }

            override fun onFailure(p0: Call<List<ProductItem>?>, p1: Throwable) {
                Log.d("MainActivity","onFailure: "+p1.message)
            }
        })
    }

    private fun getCategories() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getCategories()

        retrofitData.enqueue(object : Callback<List<String>?> {
            override fun onResponse(
                p0: Call<List<String>?>,
                p1: Response<List<String>?>
            ) {
                val responseBody = p1.body()!!
                categories = responseBody

            }

            override fun onFailure(p0: Call<List<String>?>, p1: Throwable) {
                Log.d("MainActivity","onFailure: "+p1.message)
            }
        })
    }

    public fun getCartItems(UserId:Int) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getCarts(UserId)

        retrofitData.enqueue(object : Callback<List<CartItem>?> {
            override fun onResponse(
                p0: Call<List<CartItem>?>,
                p1: Response<List<CartItem>?>
            ) {
                val responseBody = p1.body()!!
                carts = responseBody

            }

            override fun onFailure(p0: Call<List<CartItem>?>, p1: Throwable) {
                Log.d("MainActivity","onFailure: "+p1.message)
            }
        })
    }

    interface ProductCallback {
        fun onSuccess(productItem: ProductItem)
        fun onFailure(errorMessage: String)
    }

    private fun getProductId(id: Int, callback: ProductCallback) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getProductId(id)

        retrofitData.enqueue(object : Callback<ProductItem> {
            override fun onResponse(call: Call<ProductItem>, response: Response<ProductItem>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onSuccess(responseBody)
                    } else {
                        callback.onFailure("Response body is null")
                    }
                } else {
                    callback.onFailure("Response not successful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProductItem>, t: Throwable) {
                callback.onFailure("onFailure: ${t.message}")
            }
        })
    }

    private fun addCarts(cartItem: CartItem, onResult: (CartItem?) -> Unit) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.addCarts(cartItem)

        retrofitData.enqueue(object : Callback<CartItem> {
            override fun onResponse(
                p0: Call<CartItem>,
                p1: Response<CartItem>
            ) {
                val addedUser = p1.body()!!
                onResult(addedUser)

            }

            override fun onFailure(p0: Call<CartItem>, p1: Throwable) {
                onResult(null)
            }
        })
    }

}



