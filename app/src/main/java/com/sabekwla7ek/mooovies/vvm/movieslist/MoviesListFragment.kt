package com.sabekwla7ek.mooovies.vvm.movieslist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.toast

import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.base.MoooviesApplication
import com.sabekwla7ek.mooovies.dagger.AppModule
import com.sabekwla7ek.mooovies.dagger.DaggerAppComponent
import com.sabekwla7ek.mooovies.dagger.NetworkModule
import com.sabekwla7ek.mooovies.data.remote.ApiEndpoints
import com.sabekwla7ek.mooovies.network.NetworkConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesListFragment : Fragment() {

    @Inject lateinit var apiEndpoints: ApiEndpoints

    override fun onAttach(context: Context?) {
        //TODO: use the new fragment injector
        DaggerAppComponent.builder()
                .appModule(AppModule(MoooviesApplication()))
                .networkModule(NetworkModule(NetworkConstants.MOVIES_API_BASE_URL))
                .build()
                .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies_list, container, false)

        //TODO: move to repository
        apiEndpoints.getMovies().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
            activity?.toast("Movies count: ${it.results.count()}", Toast.LENGTH_LONG)
        })

        return rootView
    }


}
