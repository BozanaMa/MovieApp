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
import at.ac.fhcampuswien.movieappld02.viewmodels.MovieViewModel
import at.ac.fhcampuswien.movieappld02.widgets.MovieRow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies(),
    viewModel: MovieViewModel,
    clickOnFavorites: (Movie) -> Unit = {}
) {
    LazyColumn {
        items(movieList) { movies ->
            MovieRow(
                movie = movies,
                clickOnFavorites = { movie -> clickOnFavorites(movie) },
                clickOnItem = { movieId -> navController.navigate(MovieScreens.DetailScreen.name + "/$movieId") },
                Favorite = viewModel.checkFavoriteMovies(movies)
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: MovieViewModel
) {

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showDropDownMenu = !showDropDownMenu }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "more"
                        )
                    }
                    DropdownMenu(expanded = showDropDownMenu,
                        onDismissRequest = { showDropDownMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(MovieScreens.FavoritesScreen.name) }) {
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
        MainContent(
            navController = navController,
            viewModel = viewModel,
            clickOnFavorites = { movie ->
                if (!viewModel.checkFavoriteMovies(movie)) {
                    viewModel.addMovie(movie)
                } else {
                    viewModel.removeMovie(movie)
                }
            }
        )
    }
}

/*
        @ExperimentalAnimationApi
        @Composable
        fun MainContent(
            navController: NavController,
            movies: List<Movie> = getMovies(),
            viewModel: MovieViewModel,
            onFavoriteClick: (Movie) -> Unit = {}

/*
        ) {
            LazyColumn {
                // item { Text(text = "Header") }   // add a single composable to LazyColumn

                items(items = movies) { movie ->    // add a list of composable to LazyColumn
                    MovieRow(movie = movie) { movieId ->
                        navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                    }
                }
            }
        }
itemsIndexed(movies){ index, movie ->   // add a list of composable with index
    MovieRow(movie)
}

*/