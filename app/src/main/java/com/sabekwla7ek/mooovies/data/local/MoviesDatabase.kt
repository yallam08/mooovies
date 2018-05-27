package com.sabekwla7ek.mooovies.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sabekwla7ek.mooovies.model.MovieModel

/**
 * Created by Yahia Allam on 25/05/2018
 */
@Database(entities = [MovieModel::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}