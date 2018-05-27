package com.sabekwla7ek.mooovies.data

import com.sabekwla7ek.mooovies.data.local.MoviesDao
import com.sabekwla7ek.mooovies.data.remote.ApiEndpoints
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.Observable
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Yahia Allam on 25/05/2018
 */
class MoviesRepository @Inject constructor(
        private val moviesDao: MoviesDao, private val apiEndpoints: ApiEndpoints
) {
    /**
     * Retrieve from remote first, then update database.
     * In case of IO error in remote stream, get from db directly.
     */
    //TODO: implement retrieving from db(cache) first
    fun getMovies(): Observable<List<MovieModel>> {
        return apiEndpoints.getMovies().toObservable()
                .flatMap { t -> Observable.just(t.results) }
                .onErrorResumeNext({ throwable: Throwable ->
                    if (throwable is IOException) {
                        return@onErrorResumeNext getMoviesFromDb()
                    } else {
                        return@onErrorResumeNext Observable.error(throwable)
                    }
                }).doOnNext {
                    it.forEach {
                        moviesDao.insertMovie(it)
                    }
                }
    }

    private fun getMoviesFromDb(): Observable<List<MovieModel>> {
        return moviesDao.getMovies().toObservable()
    }
}