package com.applion.testtask.movies.ui.screen.model

import com.applion.testtask.movies.network.model.MovieResult

sealed interface MovieListUiState {
    data class Success(val movies: List<MovieResult>) : MovieListUiState
    object Loading : MovieListUiState
    object Error : MovieListUiState
}