package com.applion.testtask.movies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.applion.testtask.movies.ui.screen.MovieDetailsScreen
import com.applion.testtask.movies.ui.screen.MovieListScreen

@Composable
fun MovieNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MovieList.route,
    ) {
        composable(route = MovieList.route) {
            MovieListScreen()
        }
        composable(route = MovieDetails.route) {
            MovieDetailsScreen()
        }

    }
}