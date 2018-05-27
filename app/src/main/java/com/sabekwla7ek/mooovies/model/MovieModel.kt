package com.sabekwla7ek.mooovies.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
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
        val releaseDate: String
)