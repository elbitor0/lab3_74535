package com.stu74535.lab3_74535

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.stu74535.lab3_74535.Model.CartItem
import com.stu74535.lab3_74535.Model.OrderProduct
import com.stu74535.lab3_74535.Model.ProductItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@Composable
fun LoginButton(modifier: Modifier,username: MutableState<String>,password:MutableState<String>, isEnabled:Boolean = true, navController: NavController)
{
    Button(
        onClick = {
            try {
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(username.value, password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate(Routes.Catalog.route)
                        } else {
                            // Authentication failed
                        }
                    }
            } catch (e: Exception) {
                // Handle any exceptions that occur during authentication
            }

        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        enabled = isEnabled
    ) {
        Text(text = "Login")
    }
}
@Composable
fun SignupButton(
    modifier: Modifier,
    username: MutableState<String>,
    password: MutableState<String>,
    firstname: MutableState<String>,
    lastname: MutableState<String>,
    number: MutableState<String>,
    street: MutableState<String>,
    city: MutableState<String>,
    zipcode: MutableState<String>,
    phone: MutableState<String>,
    isEnabled: Boolean = false,
    navController: NavController
){
    Button(
        onClick = {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(username.value,password.value)
            val db = Firebase.firestore
            val user = hashMapOf(
                "city" to city.value,
                "phone" to phone.value,
                "firstName" to firstname.value,
                "id" to (auth.currentUser?.getUid() ?: 0),
                "lastName" to lastname.value,
                "lat" to Random.nextInt(),
                "long" to Random.nextInt(),
                "number" to number.value,
                "steet" to street.value,
                "zipcode" to zipcode.value
            )
            db.collection("utilisateurs").document(Random.nextInt(100000).toString()).set(user).addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
            navController.navigate(Routes.Login.route)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        enabled = isEnabled
    ) {
        Text(text = "SignUp")
    }
}

@Composable
fun Order(product: ProductItem, amount:Int)
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