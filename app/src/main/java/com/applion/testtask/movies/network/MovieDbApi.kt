package com.applion.testtask.movies.network

import com.applion.testtask.movies.network.model.MovieDetailsResult
import com.applion.testtask.movies.network.model.MovieListResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Long? = null,
        @Query("language") language: String? = null): MovieListResult

    @GET("tv/{id}")
    suspend fun getTvShowDetails(
        @Path("id") id: Long,
        @Query("language") language: String? = null): MovieDetailsResult
}