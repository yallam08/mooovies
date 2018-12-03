package com.sabekwla7ek.mooovies.vvm.movieslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sabekwla7ek.mooovies.data.MoviesRepository
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yahia Allam on 27/05/2018
 */
class MoviesListViewModel constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    val moviesLiveData = MutableLiveData<List<MovieModel>>()
    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getMovies(): Disposable =
            moviesRepository.getMovies()
                    .doOnSubscribe {
                        loadingLiveData.postValue(true)
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        moviesLiveData.postValue(it)
                        loadingLiveData.postValue(false)
                    }, {
                        errorLiveData.postValue(it.message)
                        loadingLiveData.postValue(false)
                    })
}