package at.ac.fhcampuswien.movieappld02.widgets


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.movieappld02.models.Movie
import at.ac.fhcampuswien.movieappld02.models.getMovies
import coil.compose.AsyncImage
import coil.request.ImageRequest


@ExperimentalAnimationApi
@Composable
fun MovieRow(
    movie: Movie,
    clickOnItem: (String) -> Unit = {},
    clickOnFavorites: (Movie) -> Unit = {},
    viewFavoIcon: Boolean = true,
    Favorite: Boolean = false
) {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { clickOnItem(movie.id) },

        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp),

                ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),

                    contentDescription = "movie poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )


                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie icon")

                /*
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }),

                    contentDescription = "Movie poster",
                    //modifier = Modifier.size(128.dp)
                )
                 */
            }

            var arrowInfo by remember {
                mutableStateOf(false)
            }

            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )

                if (viewFavoIcon) {
                    FavoriteIcon(
                        movie, clickOnFavorite = { movie -> clickOnFavorites(movie) },
                        Favorite = Favorite
                    )
                }

                if (!arrowInfo) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "arrow down",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(15.dp)
                            .clickable(onClick = { arrowInfo = !arrowInfo })
                    )
                }

                AnimatedVisibility(visible = arrowInfo) {
                    Column {

                        Text(
                            text = "Plot: ${movie.plot}"
                        )
                        Text(
                            text = "Actors: ${movie.actors}"
                        )
                        Text(
                            text = "Genre: ${movie.genre}"
                        )
                        Text(
                            text = "Rating: ${movie.rating}"
                        )

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "arrow up",
                            modifier = Modifier
                                .padding(10.dp)
                                .size(15.dp)
                                .clickable(onClick = { arrowInfo = !arrowInfo })
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteIcon(
    movie: Movie,
    clickOnFavorite: (Movie) -> Unit = {},
    Favorite: Boolean
) {
    var fullHeart by remember { mutableStateOf(Favorite) }
    IconButton(onClick = {
        fullHeart = !fullHeart
        clickOnFavorite(movie)
    }) {

        if (!fullHeart) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon Border",
                tint = Color.Red,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)

            )
        } else {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = Color.Red
            )
        }
    }

}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),

                    contentDescription = "Movie Image"
                )

                //Image(painter = rememberImagePainter(data = image),
                //  contentDescription = "movie image"
                //)
            }
        }
    }
}


