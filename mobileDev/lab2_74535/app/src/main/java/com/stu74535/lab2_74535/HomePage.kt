@file:OptIn(ExperimentalMaterial3Api::class)

package com.stu74535.lab2_74535

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.File
import java.net.URL

@Composable
fun HomePage(modifier:Modifier, navController: NavController ,movieArray:  SnapshotStateList<Movie>)
{
    UiScaffold(modifier = modifier,movieArray , navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiScaffold(
    modifier: Modifier = Modifier,
    movieArray: SnapshotStateList<Movie>,
    navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Dorset Cinema", textAlign = TextAlign.Center)
                },
                navigationIcon = {
                    GoToBasket(modifier = modifier, navController = navController)
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
        HomePageContent(modifier = modifier.padding(innerPadding), movieArray = movieArray, navController)
    }
}

@Composable
fun HomePageContent(modifier: Modifier, movieArray:  SnapshotStateList<Movie>, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row {
            MoviePanel(modifier = Modifier.weight(1f), movieArray, 0, navController)
            MoviePanel(modifier = Modifier.weight(1f), movieArray, 1, navController)
        }
        Row {
            MoviePanel(modifier = Modifier.weight(1f), movieArray, 2, navController)
            MoviePanel(modifier = Modifier.weight(1f), movieArray, 3, navController)
        }
        Row {
            MoviePanel(modifier = Modifier.weight(1f), movieArray, 4, navController)
            MoviePanel(modifier = Modifier.weight(1f), movieArray, 5, navController)
        }
    }
}



