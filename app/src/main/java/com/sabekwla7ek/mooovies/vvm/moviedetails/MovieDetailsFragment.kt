package com.sabekwla7ek.mooovies.vvm.moviedetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.base.BaseFragment
import com.sabekwla7ek.mooovies.extfun.loadWithCallback
import com.sabekwla7ek.mooovies.extfun.withPosterBaseURL
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie_details, container, false)

        postponeEnterTransition()
        val transition = TransitionInflater.from(activity)
                .inflateTransition(R.transition.image_shared_element_transition).setDuration(375)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition

        setupObservers()
        arguments?.getInt(ARG_MOVIE_ID)?.let {
            movieDetailsViewModel.getMovieById(it)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_MOVIE_POSTER_PATH)?.let {
            imageViewMoviePoster.transitionName = it
        }
    }

    private fun setupObservers() {
        movieDetailsViewModel.movieLiveData.observe(this, Observer { movie ->
            imageViewMoviePoster.loadWithCallback(
                    movie.posterPath.withPosterBaseURL(),
                    successCallback = { startPostponedEnterTransition() }
            )
        })
    }

    companion object {
        const val ARG_MOVIE_ID = "movie_id"
        const val ARG_MOVIE_POSTER_PATH = "movie_poster_path"
    }

}
