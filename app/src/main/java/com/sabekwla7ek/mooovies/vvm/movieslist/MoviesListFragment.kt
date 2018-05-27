package com.sabekwla7ek.mooovies.vvm.movieslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.toast
import com.sabekwla7ek.mooovies.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MoviesListFragment : Fragment() {

    @Inject
    lateinit var moviesListViewModelFactory: MoviesListViewModelFactory

    lateinit var moviesListViewModel: MoviesListViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        return super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies_list, container, false)

        moviesListViewModel =
                ViewModelProviders.of(this, moviesListViewModelFactory)
                        .get(MoviesListViewModel::class.java)

        setupObservers()

        moviesListViewModel.getMovies()

        return rootView
    }

    private fun setupObservers() {
        moviesListViewModel.moviesLiveData.observe(this, Observer {
            activity?.toast("Movies count: ${it?.count()}", Toast.LENGTH_LONG)
        })

        moviesListViewModel.errorLiveData.observe(this, Observer {
            activity?.toast("ERROR: $it", Toast.LENGTH_LONG)
        })
    }

}
