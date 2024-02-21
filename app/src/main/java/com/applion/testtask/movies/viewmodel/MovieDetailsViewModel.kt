package com.applion.testtask.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.applion.testtask.movies.repository.MovieDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieDetailsRepository
) : ViewModel() {

}