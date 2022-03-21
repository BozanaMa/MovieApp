package at.ac.fhcampuswien.movieappld02.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import at.ac.fhcampuswien.movieappld02.models.Movie
import at.ac.fhcampuswien.movieappld02.models.getMovies
import at.ac.fhcampuswien.movieappld02.navigation.MovieScreens
import at.ac.fhcampuswien.movieappld02.screens.detail.MainContent
import at.ac.fhcampuswien.movieappld02.ui.theme.MovieAppLD02Theme
import at.ac.fhcampuswien.movieappld02.widgets.MovieRow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showDropDownMenu = !showDropDownMenu }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "more"
                        )
                    }
                    DropdownMenu(expanded = showDropDownMenu,
                        onDismissRequest = { showDropDownMenu = false }) {
                        DropdownMenuItem(onClick = {}) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "favorites",
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                            Text(
                                text = "Favorites",
                                modifier = Modifier
                                    .width(90.dp)
                            )
                        }
                    }
                }
            )
        }

    )
    {
        MainContent(navController = navController)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()) {
    LazyColumn {
        // item { Text(text = "Header") }   // add a single composable to LazyColumn

        items(items = movies) { movie ->    // add a list of composable to LazyColumn
            MovieRow(movie = movie) { movieId ->
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
            }
        }
        /*
        itemsIndexed(movies){ index, movie ->   // add a list of composable with index
            MovieRow(movie)
        }

         */
    }
}