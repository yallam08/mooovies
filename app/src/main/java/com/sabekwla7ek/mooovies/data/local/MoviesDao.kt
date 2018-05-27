package com.sabekwla7ek.mooovies.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.Single

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Single<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieModel)
}
