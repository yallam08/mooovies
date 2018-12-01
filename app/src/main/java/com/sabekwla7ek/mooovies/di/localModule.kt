package com.sabekwla7ek.mooovies.di

import androidx.room.Room
import com.sabekwla7ek.mooovies.data.local.MoviesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 01/12/2018
 */

val localModule = module {

    single {
        Room.databaseBuilder(androidApplication(), MoviesDatabase::class.java, "movies_db")
                .fallbackToDestructiveMigration()
                .build()
    }

    single {
        get<MoviesDatabase>().moviesDao()
    }

}