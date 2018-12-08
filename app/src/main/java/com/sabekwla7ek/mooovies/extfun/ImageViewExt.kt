package com.sabekwla7ek.mooovies.extfun

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.sabekwla7ek.mooovies.R
import com.sabekwla7ek.mooovies.base.GlideApp

/**
 * Created by Yahia Allam on 08/12/2018
 */

fun ImageView.load(imageUrl: String) {
    GlideApp.with(this)
            .load(Uri.parse(imageUrl))
            .error(R.drawable.movie_grid_placeholder)
            .into(this)
}

/**
 * Load an image from a URL into the image view, with success and failure callbacks
 *
 * @param imageUrl the image url to be loaded
 * @param successCallback invoked when the image is loaded successfully
 * @param failureCallback invoked when the image fail to be loaded, if this callback
 *  is not provided, the successCallback wil be invoked instead
 */
fun ImageView.loadWithCallback(imageUrl: String, successCallback: () -> Unit, failureCallback: () -> Unit = successCallback) {
    GlideApp.with(this)
            .load(Uri.parse(imageUrl))
            .error(R.drawable.movie_grid_placeholder)
            .listener(object : RequestListener<Drawable?> {
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    successCallback.invoke()
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
                    failureCallback.invoke()
                    return false
                }
            })
            .into(this)
}