package com.applion.testtask.movies.navigation

interface MoviesDestination {
    val route: String
}

object MovieList : MoviesDestination {
    override val route = "list"
}

object MovieDetails : MoviesDestination {
    override val route = "details?id={id}"
}