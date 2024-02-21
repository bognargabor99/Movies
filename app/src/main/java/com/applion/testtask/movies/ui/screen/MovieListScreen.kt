package com.applion.testtask.movies.ui.screen

import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.applion.testtask.movies.Config
import com.applion.testtask.movies.R
import com.applion.testtask.movies.network.model.MovieResult
import com.applion.testtask.movies.ui.screen.model.MovieListUiState
import com.applion.testtask.movies.ui.screen.util.InfiniteCircularProgressBar
import com.applion.testtask.movies.viewmodel.MovieListViewModel

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    movieListViewModel: MovieListViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        MovieListTopAppBar()
        when (movieListViewModel.movieListState) {
            is MovieListUiState.Error -> { ErrorScreen() }
            is MovieListUiState.Loading -> { LoadingScreen() }
            is MovieListUiState.Success -> { MovieList((movieListViewModel.movieListState as MovieListUiState.Success).movies) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.movies)) },
    )
}

@Composable
fun MovieRowCard(
    movie: MovieResult
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(0.dp)
                .height(150.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if (movie.posterPath != null) "${Config.IMAGE_PREFIX_URL}${movie.posterPath}" else Config.IMAGE_PLACEHOLDER)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                    Text(movie.voteAverage.toString(), color = Color.Yellow)
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.Red)
                    Text(movie.voteAverage.toString(), color = Color.Red)
                }
                Text(text = movie.overview, maxLines = 3)
            }
        }
    }
}

@Composable
private fun ScreenContent(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        contentAlignment = Alignment.TopStart
    ) {
        content()
    }
}

@Composable
fun ErrorScreen() {
    ScreenContent {
        Text(text = stringResource(R.string.something_went_wrong))
    }
}

@Composable
fun LoadingScreen() {
    ScreenContent {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfiniteCircularProgressBar()
            Text(text = stringResource(R.string.loading))
        }
    }
}



@Composable
fun MovieList(movies: List<MovieResult>) {
    LazyColumn {
        items(movies.size) {
            MovieRowCard(movie = movies[it])
        }
    }
}