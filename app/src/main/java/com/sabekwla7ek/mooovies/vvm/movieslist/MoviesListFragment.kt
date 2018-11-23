package com.sabekwla7ek.mooovies.vvm.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.vvm.FragmentNavigator
import com.sabekwla7ek.mooovies.vvm.moviedetails.MovieDetailsFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies_list.*
import java.util.*
import javax.inject.Inject

class MoviesListFragment : Fragment() {

    private lateinit var fragmentNavigator: FragmentNavigator

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
        rv_movies_list.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 3)
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
            Toast.makeText(activity, "ERROR: $it", Toast.LENGTH_LONG).show()
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
