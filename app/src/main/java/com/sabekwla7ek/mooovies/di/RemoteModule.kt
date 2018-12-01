package com.sabekwla7ek.mooovies.di

import com.sabekwla7ek.mooovies.data.remote.ApiEndpoints
import com.sabekwla7ek.mooovies.network.ApiKeyInterceptor
import com.sabekwla7ek.mooovies.network.NetworkConstants
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Yahia Allam on 01/12/2018
 */
val remoteModule = module {

    single { Moshi.Builder().build() }

    single {
        OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    single {
        Retrofit.Builder()
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NetworkConstants.MOVIES_API_BASE_URL)
                .build()
    }

    single<ApiEndpoints> {
        get<Retrofit>().create(ApiEndpoints::class.java)
    }

}