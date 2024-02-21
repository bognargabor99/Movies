package com.applion.testtask.movies.repository

import com.applion.testtask.movies.network.MovieDbApi
import com.applion.testtask.movies.network.model.MovieDetailsResult
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val movieDbApi: MovieDbApi
) {
    suspend fun getTvShowDetails(id: Long): MovieDetailsResult {
        return movieDbApi.getMovieDetails(id)
    }
}