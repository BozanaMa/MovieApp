package at.ac.fhcampuswien.movieappld02

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigation
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

    override fun onStart() {
        super.onStart()
        Log.d("Main Activity", "onStart called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Main Activity", "onRestart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Main Activity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Main Activity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Main Activity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Main Activity", "onDestroy called")
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
        MovieNavigation()
    }
}




