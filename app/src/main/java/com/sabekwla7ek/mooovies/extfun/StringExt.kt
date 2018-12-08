package com.sabekwla7ek.mooovies.extfun

import com.sabekwla7ek.mooovies.network.NetworkConstants

/**
 * Created by Yahia Allam on 29/05/2018
 */
fun String.getShortenedDotted(length: Int): String {
    return if (this.length <= length) this
    else this.substring(0, length - 2) + " .."
}

fun String.withPosterBaseURL(): String {
    return NetworkConstants.GRID_MOVIE_POSTER_URL_BASE + this
}