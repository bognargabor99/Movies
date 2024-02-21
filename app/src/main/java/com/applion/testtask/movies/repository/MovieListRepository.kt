package com.applion.testtask.movies.repository

import com.applion.testtask.movies.network.MovieDbApi
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieDbApi: MovieDbApi
) {
}