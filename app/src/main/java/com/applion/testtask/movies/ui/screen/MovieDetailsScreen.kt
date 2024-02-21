package com.applion.testtask.movies.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.applion.testtask.movies.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Long,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        movieDetailsViewModel.fetchMovieDetails(movieId)
    }
}