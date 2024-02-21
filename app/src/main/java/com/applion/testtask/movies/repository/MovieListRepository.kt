package com.applion.testtask.movies.repository

import com.applion.testtask.movies.network.MovieDbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieDbApi: MovieDbApi
) {
    suspend fun getTopRatedMovies() = withContext(Dispatchers.IO) {
        movieDbApi.getTopRatedMovies()
    }
}