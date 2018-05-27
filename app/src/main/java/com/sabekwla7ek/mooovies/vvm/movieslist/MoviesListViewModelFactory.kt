package com.sabekwla7ek.mooovies.vvm.movieslist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Yahia Allam on 27/05/2018
 */
class MoviesListViewModelFactory
@Inject constructor(private val moviesListViewModel: MoviesListViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            return moviesListViewModel as T
        } else {
            throw IllegalArgumentException("Unknown class!!")
        }
    }
}