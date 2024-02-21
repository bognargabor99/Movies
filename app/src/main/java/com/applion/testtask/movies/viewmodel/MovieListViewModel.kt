package com.applion.testtask.movies.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applion.testtask.movies.repository.MovieListRepository
import com.applion.testtask.movies.ui.screen.model.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieListRepository
) : ViewModel() {
    var movieListState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieListState = try {
                val result = repository.getTopRatedMovies()
                MovieListUiState.Success(result.results)
            } catch (e: Exception) {
                Log.d("movie_list", "Error: ${e.localizedMessage}")
                MovieListUiState.Error
            }
        }
    }
}