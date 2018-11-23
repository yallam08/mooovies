package com.sabekwla7ek.mooovies.vvm.movieslist

import android.content.Context
import android.net.Uri
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
class MoviesListRecyclerViewAdapter(
        private val context: Context,
        var movies: List<MovieModel>,
        private val clickCallback: (movieId: Int, clickedImageView: ImageView) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<MoviesListRecyclerViewHolder>() {

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
        holder.imageViewMovieImage.transitionName = movie.posterPath

        holder.cardViewItemContainer.setOnClickListener {
            clickCallback.invoke(movie.id, holder.imageViewMovieImage)
        }
    }
}

class MoviesListRecyclerViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val cardViewItemContainer: androidx.cardview.widget.CardView = view.findViewById(R.id.card_view_movies_list_item)
    val imageViewMovieImage: ImageView = view.findViewById(R.id.image_view_movies_list_item_image)
    val textViewMovieTitle: TextView = view.findViewById(R.id.text_view_movies_list_item_title)
}
