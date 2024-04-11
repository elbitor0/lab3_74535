package com.stu74535.lab2_74535


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Back(modifier: Modifier, navController: NavController)
{
    IconButton(onClick = { navController.navigate(Routes.HomePage.route)}) {
        Icon(painter = painterResource(R.drawable.backicon), contentDescription = "BackButton")

    }
}

@Composable
fun MoviePanel(
    modifier: Modifier,
    movieArray: SnapshotStateList<Movie>,
    index: Int,
    navController: NavController
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = { navController.navigate(Routes.MovieScreen.route + "/$index") })
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Poster(modifier = Modifier.size(150.dp), movie = movieArray[index])
            Text(text = movieArray[index].name, fontSize = 11.sp, textAlign = TextAlign.Center)
        }
    }
}
@Composable
fun Poster(modifier: Modifier, movie: Movie) {

    val imageId : Int = movie.image.toInt()
    Box(modifier = modifier)
    {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Movie Poster Image",
            modifier = modifier.fillMaxSize(1f)
        )
    }


}


@Composable
fun AddSelectMovie(movieArray: SnapshotStateList<Movie>, i:Int)
{
    var updatedMovie : Movie = movieArray[i].copy()
    if (updatedMovie.seats_remaining== 0)
    {
        Icon(painter = painterResource(R.drawable.add_button_grey), contentDescription = "Unabled Add Button", modifier = Modifier.size(30.dp), tint = Color.Unspecified)
    }
    else
    {
        IconButton(onClick = {

            updatedMovie.seats_remaining  -=1
            updatedMovie.seats_selected +=1
            movieArray.remove(movieArray[i])
            movieArray.add(i,updatedMovie)
        }) {
            Icon(painter = painterResource(R.drawable.add_button), contentDescription = "Add 1 movie to selection", modifier = Modifier.size(30.dp), tint = Color.Unspecified)
        }
    }
}

@Composable
fun RemoveSelectMovie(movieArray: SnapshotStateList<Movie>, i:Int)
{
    var updatedMovie : Movie = movieArray[i].copy()
    if (updatedMovie.seats_selected == 0)
    {
        Icon(painter = painterResource(R.drawable.remove_button_grey), contentDescription = "Unabled Remove Button", modifier = Modifier.size(30.dp), tint = Color.Unspecified)
    }
    else
    {
        IconButton(onClick = {
            updatedMovie.seats_remaining  +=1
            updatedMovie.seats_selected -=1
            movieArray.remove(movieArray[i])
            movieArray.add(i,updatedMovie)
        }) {
            Icon(painter = painterResource(R.drawable.remove_button), contentDescription = "Remove 1 movie to selection", modifier = Modifier.size(30.dp), tint = Color.Unspecified)
        }
    }
}

@Composable
fun RemoveAllSelectMovie(movieArray: SnapshotStateList<Movie>, i:Int)
{
    var updatedMovie : Movie = movieArray[i].copy()
    if (updatedMovie.seats_selected != 0)
    {
        IconButton(onClick = {
            updatedMovie.seats_remaining  += updatedMovie.seats_selected
            updatedMovie.seats_selected =0
            movieArray.remove(movieArray[i])
            movieArray.add(i,updatedMovie)
        }) {
            Icon(painter = painterResource(R.drawable.binicon_removebg_preview), contentDescription = "Remove all movie selected", modifier = Modifier.size(50.dp), tint = Color.Unspecified)
        }
    }
}

@Composable
fun GoToBasket(modifier: Modifier, navController: NavController)
{
    IconButton(onClick = { navController.navigate(Routes.BasketScreen.route)}) {
        Icon(painter = painterResource(R.drawable.basket), contentDescription = "Go to Basket")

    }
}

