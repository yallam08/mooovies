package com.sabekwla7ek.mooovies.dagger

import com.sabekwla7ek.mooovies.base.MoooviesApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Yahia Allam on 21/05/2018
 */
@Module
class AppModule(private val app: MoooviesApplication) {

    @Singleton
    @Provides
    fun provideAppContext(): MoooviesApplication = app

}