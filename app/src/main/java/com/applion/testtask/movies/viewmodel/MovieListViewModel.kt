package com.applion.testtask.movies.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applion.testtask.movies.network.model.MovieResult
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

    val filteredMovies = mutableStateListOf<MovieResult>()
    var titleFilter by mutableStateOf("")

    init {
        fetchMovies()
    }

    fun onTitleFilterChange(newFilter: String) {
        this.titleFilter = newFilter
        filterMovies()
    }

    private fun filterMovies() {
        if (movieListState is MovieListUiState.Success) {
            filteredMovies.clear()
            filteredMovies.addAll((movieListState as MovieListUiState.Success).movies.filter { it.title.lowercase().contains(titleFilter) })
        }
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
            filterMovies()
        }
    }
}