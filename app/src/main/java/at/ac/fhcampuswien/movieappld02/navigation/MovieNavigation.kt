package at.ac.fhcampuswien.movieappld02.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.fhcampuswien.movieappld02.screens.detail.DetailScreen
import at.ac.fhcampuswien.movieappld02.screens.favorites.FavoritesScreen
import at.ac.fhcampuswien.movieappld02.screens.home.HomeScreen
import at.ac.fhcampuswien.movieappld02.viewmodels.MovieViewModel


@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {

        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                viewModel = movieViewModel

            )
        }

        // url: www.domain.com/detailscreen/movie=12
        composable(
            MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                viewModel = movieViewModel,
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movie")
            )
        }

        //add more routes and screens here

        composable(
            route = MovieScreens.FavoritesScreen.name,
        )

        {
            FavoritesScreen(
                navController = navController,
                viewModel = movieViewModel,

                )
        }


    }

}

