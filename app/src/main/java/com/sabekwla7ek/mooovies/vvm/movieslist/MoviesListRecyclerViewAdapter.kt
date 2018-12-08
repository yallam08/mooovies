package com.sabekwla7ek.mooovies.vvm.movieslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.extfun.getShortenedDotted
import com.sabekwla7ek.mooovies.extfun.load
import com.sabekwla7ek.mooovies.extfun.withPosterBaseURL
import com.sabekwla7ek.mooovies.model.MovieModel

/**
 * Created by Yahia Allam on 28/05/2018
 */
class MoviesListRecyclerViewAdapter(
        private val context: Context,
        var movies: List<MovieModel>,
        private val clickCallback: (movie: MovieModel, clickedImageView: ImageView) -> Unit
) : RecyclerView.Adapter<MoviesListRecyclerViewAdapter.MoviesListRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movies_list_item, parent, false)
        return MoviesListRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesListRecyclerViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MoviesListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cardViewItemContainer: CardView = view.findViewById(R.id.card_view_movies_list_item)
        private val imageViewMovieImage: ImageView = view.findViewById(R.id.imageViewMoviesListItemImage)
        private val textViewMovieTitle: TextView = view.findViewById(R.id.textViewMoviesListItemTitle)

        fun bind(movie: MovieModel) {
            this.apply {
                textViewMovieTitle.text = movie.originalTitle.getShortenedDotted(18)
                imageViewMovieImage.load(movie.posterPath.withPosterBaseURL())
                imageViewMovieImage.transitionName = movie.posterPath // unique transition name
                cardViewItemContainer.setOnClickListener {
                    clickCallback(movie, imageViewMovieImage)
                }
            }
        }
    }
}
