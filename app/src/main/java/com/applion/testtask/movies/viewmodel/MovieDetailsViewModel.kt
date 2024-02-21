package com.applion.testtask.movies.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applion.testtask.movies.repository.MovieDetailsRepository
import com.applion.testtask.movies.ui.screen.model.MovieDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieDetailsRepository
) : ViewModel() {
    var movieDetailsState: MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)
        private set

    fun fetchMovieDetails(movieId: Long) {
        viewModelScope.launch {
            movieDetailsState = try {
                val result = repository.getTvShowDetails(movieId)
                MovieDetailsUiState.Success(result)
            } catch (e: Exception) {
                Log.d("movie_list", "Error: ${e.localizedMessage}")
                MovieDetailsUiState.Error
            }
        }
    }
}