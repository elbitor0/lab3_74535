package com.stu74535.lab2_74535

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stu74535.lab2_74535.ui.theme.Lab2_74535Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    var movieArray= mutableStateListOf(
        Movie("KUNG FU PANDA 4",
            R.drawable.kunfupandaposter.toString(),
            R.drawable.pg.toString(),
            "After Po is tapped to become the Spiritual Leader of the Valley of Peace, he needs to find and train a new Dragon Warrior, while a wicked sorceress plans to re-summon all the master villains whom Po has vanquished to the spirit realm.",
            arrayOf("Jack Black", "Awkwafina"),
            94,
            Random.nextInt(0,15)),
        Movie("GHOSTBUSTERS: FROZEN EMPIRE",
            R.drawable.ghostbustersposter.toString(),
            R.drawable._2a.toString(),
            "In Ghostbusters: Frozen Empire, the Spengler family returns to where it all started – the iconic New York City firehouse – to team up with the original Ghostbusters.",
            arrayOf("Paul Rudd", "Bill Murray"),
            144,
            Random.nextInt(0,15)
        ),
        Movie("GODZILLA X KONG: THE NEW EMPIRE",
            R.drawable.godzillaposter.toString(),
            R.drawable._2a.toString(),
            "The new installment in the Monsterverse puts the mighty Kong and the fearsome Godzilla against a colossal deadly threat hidden within our world.",
            arrayOf("Rebecca Hall", "Dan Stevens"),
            115,
            Random.nextInt(0,15)
        ),
        Movie("DUNE: PART TWO",
            R.drawable.duneposter.toString(),
            R.drawable._2a.toString(),
            "Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family.",
            arrayOf("Timothée Chalamet", "Florence Pugh"),
            166,
            Random.nextInt(0,15)
        ),
        Movie("MIGRATION",
            R.drawable.migrationposter.toString(),
            R.drawable.pg.toString(),
            "A family of ducks decides to leave the safety of a New England pond for an adventurous trip to Jamaica. However, their well-laid plans quickly go awry when they get lost and wind up in New York City.",
            arrayOf("Elizabeth Banks", "Danny DeVito"),
            90,
            Random.nextInt(0,15)
        ),
        Movie("IMMACULATE",
            R.drawable.immaculateposter.toString(),
            R.drawable._6.toString(),
            "Sydney Sweeney stars as devout nun Cecilia, trapped in an ancient Italian convent that conceals twisted secrets and unspeakable horrors." ,
            arrayOf("Sydney Sweeney", "Benedetta Porcaroli"),
            89,
            Random.nextInt(0,15)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2_74535Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(modifier = Modifier, movieArray = movieArray)
                }
            }
        }
    }


}