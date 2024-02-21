package com.applion.testtask.movies.ui.screen.model

import com.applion.testtask.movies.network.model.MovieDetailsResult

sealed interface MovieDetailsUiState {
    data class Success(val movie: MovieDetailsResult) : MovieDetailsUiState
    object Loading : MovieDetailsUiState
    object Error : MovieDetailsUiState
}