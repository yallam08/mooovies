package com.sabekwla7ek.mooovies.model

import com.squareup.moshi.Json

/**
 * Created by Yahia Allam on 25/05/2018
 */
data class ResponseModel(
        @field:Json(name = "page")
        val page: Int,

        @field:Json(name = "total_results")
        val total_results: Int,

        @field:Json(name = "total_pages")
        val total_pages: Int,

        @field:Json(name = "results")
        val results: List<MovieModel>
)