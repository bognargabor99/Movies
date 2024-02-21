package com.applion.testtask.movies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.applion.testtask.movies.navigateSingleTopTo
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
            MovieListScreen() {
                val route = MovieDetails.route.replace("{id}", it.id.toString())
                navController.navigateSingleTopTo(route)
            }
        }
        composable(
            route = MovieDetails.route,
            arguments = listOf(
                navArgument("id") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")!!
            MovieDetailsScreen(movieId = id)
        }

    }
}