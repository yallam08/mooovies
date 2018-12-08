package com.sabekwla7ek.mooovies.vvm.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sabekwla7ek.mooovies.data.MoviesRepository
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yahia Allam on 03/06/2018
 */
class MovieDetailsViewModel constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    val movieLiveData = MutableLiveData<MovieModel>()
    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getMovieById(movieId: Int) {
        moviesRepository.getMovieById(movieId)
                .doOnSubscribe {
                    loadingLiveData.postValue(true)
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    movieLiveData.postValue(it)
                    loadingLiveData.postValue(false)
                }, {
                    errorLiveData.postValue(it.message)
                    loadingLiveData.postValue(false)
                })
    }
}