package com.stu74535.lab2_74535

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MovieScreen(modifier: Modifier, navController: NavController, movieArray: SnapshotStateList<Movie>, index: Int?)
{
    if (index != null)
        MovieScaffold(modifier, movieArray, navController,index)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScaffold(
    modifier: Modifier = Modifier,
    movieArray: SnapshotStateList<Movie>,
    navController: NavController,
    i:Int)
{
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(movieArray[i].name)
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
        MovieScreenContent(modifier = modifier.padding(innerPadding), movieArray,i)
    }
}
@Composable
fun MovieScreenContent(modifier: Modifier, movieArray: SnapshotStateList<Movie>,i:Int) {

    var starringString: String = ""
    var j = 0

            // Generating the string for starring actors
    while (j < movieArray[i].starring.size - 1) {
        starringString += movieArray[i].starring[j] + ", "
        j += 1
    }
    starringString += movieArray[i].starring.last()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Poster(modifier = Modifier.size(300.dp), movie = movieArray[i])
        Row {
            Text(text = movieArray[i].name)
            CertificationIcon(modifier = Modifier.size(20.dp), movie = movieArray[i])
        }

        Text(text = "Starring: $starringString")
        val hours = movieArray[i].running_time_mins / 60
        val minutes = movieArray[i].running_time_mins % 60

        Text(text = "Running Time: $hours hr $minutes mins")
        Text(text = movieArray[i].description)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Select Seats  ")
                RemoveSelectMovie(movieArray, i)
                Text(text = " ${movieArray[i].seats_selected} ")
                AddSelectMovie(movieArray, i)


            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                SeatIcon(modifier = Modifier.size(24.dp))
                Text(text = "${movieArray[i].seats_remaining} seats remaining")
            }
        }
    }
}
@Composable
fun SeatIcon(modifier: Modifier)
{
    Icon(painter = painterResource(R.drawable.seaticon), contentDescription = "Seat Icon", modifier = Modifier.size(40.dp))
}

@Composable
fun CertificationIcon(modifier: Modifier, movie: Movie)
{
    val imageId : Int = movie.certification.toInt()
    Box(modifier = modifier)
    {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Movie Certification Image",
            modifier = modifier.fillMaxSize(1f)
        )
    }
}

