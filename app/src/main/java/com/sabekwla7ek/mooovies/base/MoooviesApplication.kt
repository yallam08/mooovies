package com.sabekwla7ek.mooovies.base

import android.app.Application
import com.sabekwla7ek.mooovies.data.dataModule
import com.sabekwla7ek.mooovies.di.localModule
import com.sabekwla7ek.mooovies.di.remoteModule
import com.sabekwla7ek.mooovies.vvm.viewModelModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Yahia Allam on 21/05/2018
 */
class MoooviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(remoteModule, localModule, dataModule, viewModelModule))
    }
}