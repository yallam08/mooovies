package com.sabekwla7ek.mooovies.dagger

import android.arch.persistence.room.Room
import com.sabekwla7ek.mooovies.base.MoooviesApplication
import com.sabekwla7ek.mooovies.data.local.MoviesDao
import com.sabekwla7ek.mooovies.data.local.MoviesDatabase
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListViewModel
import com.sabekwla7ek.mooovies.vvm.movieslist.MoviesListViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Yahia Allam on 21/05/2018
 */
@Module
class AppModule(private val app: MoooviesApplication) {

    @Singleton
    @Provides
    fun provideAppContext(): MoooviesApplication = app

    @Singleton
    @Provides
    fun provideMoviesDatabase(app: MoooviesApplication): MoviesDatabase =
            Room.databaseBuilder(app, MoviesDatabase::class.java, "movies_db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton
    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao =
            database.moviesDao()

    @Provides
    fun provideMoviesListViewModelFactory(moviesListViewModel: MoviesListViewModel) =
            MoviesListViewModelFactory(moviesListViewModel)

}