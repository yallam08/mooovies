package com.sabekwla7ek.mooovies.dagger

import com.sabekwla7ek.mooovies.vvm.MainActivity
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Yahia Allam on 27/05/2018
 */
@Module
abstract class ContributorsModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment

}