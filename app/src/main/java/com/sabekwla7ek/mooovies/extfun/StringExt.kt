package com.sabekwla7ek.mooovies.extfun

/**
 * Created by Yahia Allam on 29/05/2018
 */
fun String.getShortenedDotted(length: Int): String {
    return if (this.length <= length) this
    else this.substring(0, length - 2) + " .."
}