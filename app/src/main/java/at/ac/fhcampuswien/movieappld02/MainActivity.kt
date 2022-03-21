package at.ac.fhcampuswien.movieappld02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.movieappld02.navigation.MovieNavigation
import at.ac.fhcampuswien.movieappld02.screens.home.HomeScreen
import at.ac.fhcampuswien.movieappld02.ui.theme.MovieAppLD02Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppLD02Theme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppLD02Theme {
        HomeScreen()
    }
}




