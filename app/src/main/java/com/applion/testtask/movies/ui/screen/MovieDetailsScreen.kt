package com.applion.testtask.movies.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.applion.testtask.movies.Config
import com.applion.testtask.movies.R
import com.applion.testtask.movies.network.model.Genre
import com.applion.testtask.movies.network.model.MovieDetailsResult
import com.applion.testtask.movies.ui.screen.model.MovieDetailsUiState
import com.applion.testtask.movies.ui.screen.util.ErrorScreen
import com.applion.testtask.movies.ui.screen.util.LoadingScreen
import com.applion.testtask.movies.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Long,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        movieDetailsViewModel.fetchMovieDetails(movieId)
    }

    when (movieDetailsViewModel.movieDetailsState) {
        is MovieDetailsUiState.Error -> { ErrorScreen() }
        is MovieDetailsUiState.Loading -> { LoadingScreen() }
        is MovieDetailsUiState.Success -> { DetailedMovie((movieDetailsViewModel.movieDetailsState as MovieDetailsUiState.Success).movie) }
    }
}

@Composable
fun DetailedMovie(movie: MovieDetailsResult) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageWithTitle(movie)
        DetailsRow(movie)
        CategoryBadges(movie.genres)
        MovieOverView(movie.overview)
    }
}

@Composable
fun ImageWithTitle(movie: MovieDetailsResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.elevatedCardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${Config.IMAGE_PREFIX_URL}${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                contentScale = ContentScale.FillWidth,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(
                        Brush.verticalGradient(
                            0F to Color.Transparent,
                            0.3F to Color.Black.copy(alpha = 0.5F),
                            1F to Color.Black.copy(alpha = 1F)
                        )
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = Color.White)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(20.dp), imageVector = Icons.Filled.Star, contentDescription = null, tint = Color(200, 165, 0))
                    Text(movie.voteAverage.toString().take(3), color = Color(200, 165, 0), style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

@Composable
fun DetailsRow(movie: MovieDetailsResult) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SpecificDetail(title = stringResource(R.string.status), value = movie.status)
        DetailDivider()
        SpecificDetail(title = stringResource(R.string.popularity), value = movie.popularity.toString())
        DetailDivider()
        SpecificDetail(title = stringResource(R.string.language), value = movie.originalLanguage.uppercase())
    }
}

@Composable
fun DetailDivider() {
    Divider(modifier = Modifier
        .height(40.dp)
        .width(1.dp), color = Color.Gray)
}

@Composable
fun SpecificDetail(title: String, value: String){
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, color = Color.Gray, style = MaterialTheme.typography.titleSmall)
        Text(text = value, style = MaterialTheme.typography.titleMedium)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryBadges(genres: List<Genre>) {
    FlowRow(
        modifier = Modifier.padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (genre in genres)
            CategoryBadge(genre.name)
    }
}

@Composable
fun CategoryBadge(text: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Red, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 3.dp, horizontal = 10.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp)),
                text = text,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun MovieOverView(overview: String) {
    Text(modifier = Modifier.padding(horizontal = 12.dp), text = overview, color = Color.Gray)
}