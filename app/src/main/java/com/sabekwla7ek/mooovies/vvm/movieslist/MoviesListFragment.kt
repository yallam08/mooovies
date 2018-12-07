package com.sabekwla7ek.mooovies.vvm.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.vvm.FragmentNavigator
import com.sabekwla7ek.mooovies.vvm.moviedetails.MovieDetailsFragment
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MoviesListFragment : Fragment() {

    private lateinit var fragmentNavigator: FragmentNavigator

    private val moviesListViewModel: MoviesListViewModel by viewModel()
    private lateinit var moviesListAdapter: MoviesListRecyclerViewAdapter

    override fun onAttach(context: Context?) {
        try {
            fragmentNavigator = activity as FragmentNavigator
        } catch (e: ClassCastException) {
            throw ClassCastException("${activity.toString()} must implement ${FragmentNavigator::class.simpleName}")
        }

        return super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies_list, container, false)

        setupObservers()
        moviesListViewModel.getMovies()

        return rootView
    }

    // Called immediately after onCreateView(..)
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
        rvMoviesList.layoutManager = GridLayoutManager(activity, 3)
        rvMoviesList.setHasFixedSize(true)
        rvMoviesList.adapter = moviesListAdapter
    }

    private fun setupObservers() {
        moviesListViewModel.loadingLiveData.observe(this, Observer { isLoading ->
            if (isLoading) {
                progressBarLoading.visibility = VISIBLE
                rvMoviesList.visibility = GONE
            } else {
                progressBarLoading.visibility = GONE
                rvMoviesList.visibility = VISIBLE
            }
        })

        moviesListViewModel.moviesLiveData.observe(this, Observer { moviesList ->
            if (moviesList != null) {
                moviesListAdapter.movies = moviesList
                moviesListAdapter.notifyDataSetChanged()
            }
        })

        moviesListViewModel.errorLiveData.observe(this, Observer { errorStr ->
            Toast.makeText(activity, "ERROR: $errorStr", Toast.LENGTH_LONG).show()
        })
    }

    private fun moviesGridItemClickCallback(movieId: Int, clickedImageView: ImageView) {
        val movieDetailsFragment = MovieDetailsFragment()
        val bundle = Bundle()
        bundle.putInt(MovieDetailsFragment.ARG_MOVIE_ID, movieId)
        movieDetailsFragment.arguments = bundle
        fragmentNavigator.navigateToFragmentAndSaveState(this, movieDetailsFragment, clickedImageView)
    }
}
