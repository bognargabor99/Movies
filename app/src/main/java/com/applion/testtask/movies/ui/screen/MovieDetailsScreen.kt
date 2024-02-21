package com.applion.testtask.movies.ui.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.applion.testtask.movies.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Long,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {

}