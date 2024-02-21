package com.applion.testtask.movies.network

import com.applion.testtask.movies.network.model.MovieDetailsResult
import com.applion.testtask.movies.network.model.MovieListResult
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieListResult

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Long
    ): MovieDetailsResult
}