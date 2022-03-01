package at.ac.fhcampuswien.movieappld02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.movieappld02.ui.theme.MovieAppLD02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppLD02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val movies = listOf("Reservoir Dogs", "Harry Potter", "Marry Poppins", "GOT", "Brügge sehen und sterben")
                    MainContent(movies = movies)
                }
            }
        }
    }
}

@Composable
fun MainContent(movies: List<String> = listOf("Harry Potter", "LOTR", "Marry Poppins", "GOT", "Brügge sehen und sterben")){
    LazyColumn {
        item { Text(text = "Header") }

        items(items = movies) { movie ->
            MovieRow(movie = movie)
        }
        /*
        itemsIndexed(movies){ index, movie ->
            MovieRow(movie)
        }

         */

    }
}

@Composable
fun MovieRow(movie: String = "Harry Potter") {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
           Surface(modifier = Modifier
               .size(100.dp)
               .padding(12.dp)
           ) {
               Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
           }

           Text(text = movie) 
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppLD02Theme {
        MainContent()
    }
}