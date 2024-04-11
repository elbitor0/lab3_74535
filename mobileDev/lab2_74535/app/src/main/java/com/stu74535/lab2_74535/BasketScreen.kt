package com.stu74535.lab2_74535

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BasketScreen(modifier: Modifier, navController: NavController, movieArray:  SnapshotStateList<Movie>)
{
    BasketScaffold(modifier,movieArray =movieArray  , navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScaffold(
    modifier: Modifier = Modifier,
    title: String = "My Basket",
    movieArray:  SnapshotStateList<Movie>,
    navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(title)
                },
                navigationIcon = { Back(modifier = modifier, navController = navController) }
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
        BasketPageContent(modifier = modifier.padding(innerPadding), movieArray = movieArray)
    }
}

@Composable
fun BasketPageContent(modifier: Modifier, movieArray: SnapshotStateList<Movie>) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.Top
    ){
        items(movieArray.filter { it.seats_selected != 0 }) { movie ->
            MovieCase(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                movieArray = movieArray,
                i = movieArray.indexOf(movie)
            )
        }
    }
}

@Composable
fun MovieCase(modifier: Modifier, movieArray: SnapshotStateList<Movie>, i: Int) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Poster(modifier = Modifier.size(100.dp), movie = movieArray[i])
            Column(modifier = Modifier.width(160.dp)) {
                Text(text = movieArray[i].name)
                Text(text = "Place Selected: ${movieArray[i].seats_selected}",modifier=modifier.size(2.dp,100.dp))
            }
            RemoveAllSelectMovie(movieArray, i)
        }



}
