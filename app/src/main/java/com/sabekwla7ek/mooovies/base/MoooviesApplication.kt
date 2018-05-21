package com.sabekwla7ek.mooovies.base

import android.app.Application
import com.sabekwla7ek.mooovies.dagger.AppModule
import com.sabekwla7ek.mooovies.dagger.DaggerAppComponent
import com.sabekwla7ek.mooovies.dagger.NetworkModule
import com.sabekwla7ek.mooovies.network.NetworkConstants

/**
 * Created by Yahia Allam on 21/05/2018
 */
class MoooviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(NetworkConstants.MOVIES_API_BASE_URL))
                .build()
                .inject(this)
    }
}