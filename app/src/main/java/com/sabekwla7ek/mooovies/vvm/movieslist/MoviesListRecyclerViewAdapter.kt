package com.sabekwla7ek.mooovies.vvm.movieslist

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.base.GlideApp
import com.sabekwla7ek.mooovies.extfun.getShortenedDotted
import com.sabekwla7ek.mooovies.model.MovieModel
import com.sabekwla7ek.mooovies.network.NetworkConstants

/**
 * Created by Yahia Allam on 28/05/2018
 */
class MoviesListRecyclerViewAdapter(var movies: List<MovieModel>, private val context: Context)
    : RecyclerView.Adapter<MoviesListRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movies_list_item, parent, false)
        return MoviesListRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesListRecyclerViewHolder, position: Int) {
        val movie = movies[position]
        holder.textViewMovieTitle.text = movie.originalTitle.getShortenedDotted(18)
        GlideApp.with(context)
                .load(Uri.parse(NetworkConstants.GRID_MOVIE_POSTER_URL_BASE + movie.posterPath))
                .error(R.drawable.movie_grid_placeholder)
                .into(holder.imageViewMovieImage)

    }
}

class MoviesListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageViewMovieImage: ImageView = view.findViewById(R.id.image_view_movies_list_item_image)
    val textViewMovieTitle: TextView = view.findViewById(R.id.text_view_movies_list_item_title)
}
