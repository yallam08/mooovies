package com.sabekwla7ek.mooovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Created by Yahia Allam on 21/05/2018
 */
@Entity(tableName = "movie")
data class MovieModel(
        @PrimaryKey
        @field:Json(name = "id")
        val id: Int,

        @field:Json(name = "original_title")
        val originalTitle: String,

        @field:Json(name = "overview")
        val overview: String,

        @field:Json(name = "vote_average")
        val voteAverage: Double,

        @field:Json(name = "release_date")
        val releaseDate: String,

        @field:Json(name = "vote_count")
        val voteCount: Int,

        @field:Json(name = "poster_path")
        val posterPath: String,

        @field:Json(name = "backdrop_path")
        val backdropPath: String?
)