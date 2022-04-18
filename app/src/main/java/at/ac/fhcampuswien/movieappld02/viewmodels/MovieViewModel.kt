package at.ac.fhcampuswien.movieappld02.viewmodels

import android.provider.ContactsContract
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.movieappld02.models.Movie


class MovieViewModel : ViewModel() {
    private var movies = mutableStateListOf<Movie>()

    fun addMovie(movie: Movie) {
        movies.add(movie)
    }

    fun removeMovie(movie: Movie) {
        movies.remove(movie)
    }

    fun getAllMovies(): List<Movie> {
        return movies
    }

    fun checkFavoriteMovies(movie: Movie): Boolean {
        return movies.contains(movie)
    }
}