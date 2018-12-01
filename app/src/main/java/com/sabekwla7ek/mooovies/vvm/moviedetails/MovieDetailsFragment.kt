package com.sabekwla7ek.mooovies.vvm.moviedetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabekwla7ek.mooovies.R

class MovieDetailsFragment : Fragment() {

    companion object {
        const val ARG_MOVIE_ID = "movie_id"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }


}
