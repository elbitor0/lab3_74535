package com.stu74535.lab3_74535

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.UUID

@Composable
fun LoginButton(modifier: Modifier,username: MutableState<String>,password:MutableState<String>)
{
    Button(
        onClick = { },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Login")
    }
}

@Composable
fun SignupButton(modifier: Modifier, username: MutableState<String>, password: MutableState<String>)
{
    Button(
        onClick = { },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "SignUp")
    }
}

@Composable
fun Order(product: ProductItem,amount:Int)
{

}

@Composable
fun Add(amount: MutableState<Int>)
{

        Button(onClick = { amount.value +=1 }) {
            Icon(painter = painterResource(id = R.drawable.add_button), contentDescription = "add button")
            R.drawable.add_button

    }


}

@Composable
fun Remove(amount: MutableState<Int>)
{
    if (amount.value<=0)
        Icon(painter = painterResource(id = R.drawable.remove_button_grey), contentDescription = "remove button grey")
    else
    {
        Button(onClick = { amount.value -=1 }) {
            Icon(painter = painterResource(id = R.drawable.remove_button), contentDescription = "remove button")

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Confirm(amount: MutableState<Int>, products: List<OrderProduct>, userId: Int) {
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cartService = retrofit.create(ApiInterface::class.java)

    Button(onClick = {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val newCart = CartItem(0, userId, currentDate.format(formatter), products, 0)

        // Make the POST request to create a new cart
        sendCart(cartService,newCart)
    }) {
        Text("Confirm")
    }
}

fun sendCart(cartService: ApiInterface, cart: CartItem) {
    // Make the POST request to create a new cart
    val cartResponse = cartService.addCarts(cart)
    // Handle the response if needed
}

@Composable
fun AddToCart(amount:MutableState<Int>, product: ProductItem, currentCart : MutableList<OrderProduct>)
{
    currentCart.add(OrderProduct(product.id,amount.value))
    amount.value =0
}