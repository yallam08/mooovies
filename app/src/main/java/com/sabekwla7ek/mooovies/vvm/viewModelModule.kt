package com.sabekwla7ek.mooovies.vvm

import com.sabekwla7ek.mooovies.vvm.moviedetails.MovieDetailsViewModel
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 01/12/2018
 */
val viewModelModule = module {

    viewModel {
        MoviesListViewModel(get())
    }

    viewModel {
        MovieDetailsViewModel(get())
    }

}