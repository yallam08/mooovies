package com.sabekwla7ek.mooovies.data

import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 01/12/2018
 */
val dataModule = module {

    factory {
        MoviesRepository(get(), get())
    }

}