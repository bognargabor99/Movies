package com.applion.testtask.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.applion.testtask.movies.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val repository: MovieListRepository
) : ViewModel() {

}