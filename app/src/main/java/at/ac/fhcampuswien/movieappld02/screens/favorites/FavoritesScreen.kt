@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package at.ac.fhcampuswien.movieappld02.screens.favorites

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.movieappld02.viewmodels.MovieViewModel
import at.ac.fhcampuswien.movieappld02.widgets.MovieRow
import androidx.lifecycle.viewmodel.compose.viewModel
import at.ac.fhcampuswien.movieappld02.models.Movie
import at.ac.fhcampuswien.movieappld02.navigation.MovieScreens

@Composable
fun FavoritesScreen(
    navController: NavController = rememberNavController(),
    viewModel: MovieViewModel = viewModel(),
    favoriteMovies: List<Movie> = viewModel.getAllMovies()

) {

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()

                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "My Favorite Movies")
                }
            }
        }
    )
    {
        MainContent(viewModel = viewModel, navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(
    viewModel: MovieViewModel = viewModel(),
    navController: NavController = rememberNavController(),
) {
    Column {
        for (i in viewModel.getAllMovies()) {
            MovieRow(movie = i, viewFavoIcon = false,
                clickOnItem = { movieId -> navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId") })
        }
    }
}






