package com.applion.testtask.movies.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResult(
    val page: Long,
    val results: List<MovieResult>,
    @SerialName("total_results") val totalResults: Long,
    @SerialName("total_pages") val totalPages: Long
)