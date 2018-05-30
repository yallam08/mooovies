package com.sabekwla7ek.mooovies.vvm.movieslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.toast
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.vvm.FragmentNavigator
import com.sabekwla7ek.mooovies.vvm.moviedetails.MovieDetailsFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject

class MoviesListFragment : Fragment() {

    lateinit var fragmentNavigator: FragmentNavigator

    @Inject
    lateinit var moviesListViewModelFactory: MoviesListViewModelFactory
    private lateinit var moviesListViewModel: MoviesListViewModel
    private lateinit var moviesListAdapter: MoviesListRecyclerViewAdapter

    override fun onAttach(context: Context?) {
        try {
            fragmentNavigator = activity as FragmentNavigator
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement " + FragmentNavigator::class.simpleName)
        }

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
        moviesListViewModel.getMovies() //TODO: implement loading indicator

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesRecyclerView()
    }

    private fun initMoviesRecyclerView() {
        moviesListAdapter = MoviesListRecyclerViewAdapter(
                context = requireContext(),
                movies = moviesListViewModel.moviesLiveData.value ?: ArrayList(),
                clickCallback = this::moviesGridItemClickCallback
        )
        rv_movies_list.layoutManager = GridLayoutManager(activity, 3)
        rv_movies_list.setHasFixedSize(true)
        rv_movies_list.adapter = moviesListAdapter
    }

    private fun setupObservers() {
        moviesListViewModel.moviesLiveData.observe(this, Observer {
            if (it != null) {
                moviesListAdapter.movies = it
                moviesListAdapter.notifyDataSetChanged()
            }
        })

        moviesListViewModel.errorLiveData.observe(this, Observer {
            activity?.toast("ERROR: $it", Toast.LENGTH_LONG)
        })
    }

    private fun moviesGridItemClickCallback(movieId: Int) {
        val movieDetailsFragment = MovieDetailsFragment()
        val bundle = Bundle()
        bundle.putInt(MovieDetailsFragment.ARG_MOVIE_ID, movieId)
        movieDetailsFragment.arguments = bundle
        fragmentNavigator.navigateToFragment(movieDetailsFragment)
    }

}
