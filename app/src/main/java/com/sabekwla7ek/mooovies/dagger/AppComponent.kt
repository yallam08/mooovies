package com.sabekwla7ek.mooovies.dagger

import com.sabekwla7ek.base.MoooviesApplication
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yahia Allam on 21/05/2018
 */
@Singleton
@Component(
        modules = [AppModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(app: MoooviesApplication)
}