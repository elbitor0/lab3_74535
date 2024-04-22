package com.stu74535.lab3_74535

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun Login(modifier: Modifier = Modifier,navController: NavController ) {

    val username = remember {
        { mutableStateOf("")}
    }
    val password = remember {
        { mutableStateOf("")}
    }
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,horizontalAlignment = Alignment.CenterHorizontally) {
        PadlockImage(modifier = modifier.size(150.dp))
        AuthField(modifier = modifier, label = "Username", inputValue = username.invoke())
        AuthField(modifier = modifier, label = "Password", inputValue = password.invoke())
        LoginButton(modifier = modifier, username = username.invoke(), password =password.invoke() )
    }
}
@Composable
fun SignUp(modifier: Modifier,navController: NavController)
{
    val username = remember {
        { mutableStateOf("")}
    }
    val password = remember {
        { mutableStateOf("")}
    }
    Column(modifier = modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        PadlockImage(modifier = modifier.size(150.dp))
        AuthField(modifier = modifier, label = "Username", inputValue = username.invoke())
        AuthField(modifier = modifier, label = "Password", inputValue = password.invoke())
        AuthField(modifier = modifier, label = "Confirm Password",  inputValue = password.invoke())
        SignupButton(modifier = modifier, username = username.invoke(), password =password.invoke() )
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
fun AuthField(modifier: Modifier, label: String, inputValue:MutableState<String>) {

    OutlinedTextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        singleLine = true,
        label = {
            Text(text = label)
        }
    )
}



