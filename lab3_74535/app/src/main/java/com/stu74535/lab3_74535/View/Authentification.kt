package com.stu74535.lab3_74535.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stu74535.lab3_74535.LoginButton
import com.stu74535.lab3_74535.R
import com.stu74535.lab3_74535.SignupButton


@Composable
fun Login(modifier: Modifier = Modifier,navController: NavController ) {

    val username = remember {
         mutableStateOf("")
    }
    val password = remember{ mutableStateOf("")}

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,horizontalAlignment = Alignment.CenterHorizontally) {
        PadlockImage(modifier = modifier.size(150.dp))
        AuthField(modifier = modifier, label = "Username", inputValue = username)
        AuthField(modifier = modifier, label = "Password", inputValue = password)
        LoginButton(modifier = modifier, username = username, password =password, navController = navController )
    }
}
@Composable
fun SignUp(modifier: Modifier,navController: NavController)
{
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val firstname = remember { mutableStateOf("") }
    val lastname = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val zipcode = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    LazyColumn(modifier = modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            PadlockImage(modifier = Modifier.size(150.dp))
        }
        item {
            AuthField(
                modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), label = "Username", inputValue = username)
        }
        item {
            AuthField(
                modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), label = "Password", inputValue = password)
        }
        item {
            AuthField(
                modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), label = "Confirm Password",  inputValue = password)
        }
        item {
            AuthField(
                modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), label = "Firstname", inputValue = firstname)
        }
        item {
            AuthField(
                modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), label = "Lastname", inputValue = lastname)
        }
        item {
            Text(text = ("Enter your adress in the following format:Number/Street/City/ZipCode"))
        }
        item {
            Row {
                AuthField(modifier = Modifier.size(100.dp,70.dp), label = "", inputValue = number)
                AuthField(modifier = Modifier.size(100.dp,70.dp), label = "", inputValue = street)
                AuthField(modifier = Modifier.size(100.dp,70.dp), label = "", inputValue = city)
                AuthField(modifier = Modifier.size(100.dp,70.dp), label = "", inputValue = zipcode)
            }
        }
        item {
            AuthField(
                modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), label = "Phone Number", inputValue = phone)
        }

        item {
            SignupButton(
                modifier = modifier,
                username = username,
                password = password,
                firstname = firstname,
                lastname = lastname,
                number = number,
                street = street,
                city = city,
                zipcode = zipcode,
                phone = phone, // assuming you want to pass an empty string for phone
                isEnabled = true,
                navController = navController
            )
        }
    }
}

@Composable
fun PadlockImage(modifier: Modifier) {

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.padlock),
            contentDescription = "Padlock Image",
            modifier = modifier
        )
    }
}

@Composable
fun AuthField(modifier: Modifier, label: String, inputValue:  MutableState<String>) {

    OutlinedTextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        modifier = modifier,
        singleLine = true,
        label = {
            Text(text = label)
        }
    )
}



