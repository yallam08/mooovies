package com.sabekwla7ek.mooovies.dagger

import com.sabekwla7ek.mooovies.base.MoooviesApplication
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yahia Allam on 21/05/2018
 */
@Singleton
@Component(
        modules = [AppModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(app: MoooviesApplication)
    fun inject(moviesListFragment: MoviesListFragment)
}