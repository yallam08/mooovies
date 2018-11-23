package com.sabekwla7ek.mooovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.Single

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Single<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieModel)
}
