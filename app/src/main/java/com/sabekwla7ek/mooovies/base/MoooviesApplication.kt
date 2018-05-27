package com.sabekwla7ek.mooovies.base

import android.app.Activity
import android.app.Application
import com.sabekwla7ek.mooovies.dagger.AppModule
import com.sabekwla7ek.mooovies.dagger.DaggerAppComponent
import com.sabekwla7ek.mooovies.dagger.NetworkModule
import com.sabekwla7ek.mooovies.network.NetworkConstants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Yahia Allam on 21/05/2018
 */
class MoooviesApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(NetworkConstants.MOVIES_API_BASE_URL))
                .build()
                .inject(this)
    }
}