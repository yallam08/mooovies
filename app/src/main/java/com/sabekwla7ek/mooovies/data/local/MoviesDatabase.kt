package com.sabekwla7ek.mooovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sabekwla7ek.mooovies.model.MovieModel

/**
 * Created by Yahia Allam on 25/05/2018
 */
@Database(entities = [MovieModel::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}