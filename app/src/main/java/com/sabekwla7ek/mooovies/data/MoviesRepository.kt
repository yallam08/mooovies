package com.sabekwla7ek.mooovies.data

import com.sabekwla7ek.mooovies.data.local.MoviesDao
import com.sabekwla7ek.mooovies.data.remote.ApiEndpoints
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException

/**
 * Created by Yahia Allam on 25/05/2018
 */
class MoviesRepository constructor(
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
                .onErrorResumeNext { throwable: Throwable ->
                    if (throwable is IOException) {
                        //TODO: what if db is empty?
                        return@onErrorResumeNext getMoviesFromDb()
                    } else {
                        return@onErrorResumeNext Observable.error(throwable)
                    }
                }.doOnNext {
                    it.forEach { movie ->
                        moviesDao.insertMovie(movie)
                    }
                }
    }

    fun getMovieById(movieId: Int): Single<MovieModel> {
        return moviesDao.getMovieById(movieId)
    }

    private fun getMoviesFromDb(): Observable<List<MovieModel>> {
        return moviesDao.getMovies().toObservable()
    }

}