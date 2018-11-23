package com.sabekwla7ek.mooovies.vvm.movieslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sabekwla7ek.mooovies.data.MoviesRepository
import com.sabekwla7ek.mooovies.model.MovieModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Yahia Allam on 27/05/2018
 */
class MoviesListViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    val moviesLiveData = MutableLiveData<List<MovieModel>>()
    val errorLiveData = MutableLiveData<String>()

    fun getMovies(): Disposable =
            moviesRepository.getMovies()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        moviesLiveData.postValue(it)
                    }, {
                        errorLiveData.postValue(it.message)
                    })
}