package com.sabekwla7ek.mooovies.dagger

import com.sabekwla7ek.mooovies.data.remote.ApiEndpoints
import com.sabekwla7ek.mooovies.network.ApiKeyInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Yahia Allam on 21/05/2018
 */
@Module
class NetworkModule(private val apiBaseUrl: String) {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(apiBaseUrl)
                .build()
    }

    @Singleton
    @Provides
    fun provideApiEndpoints(retrofit: Retrofit): ApiEndpoints = retrofit.create(ApiEndpoints::class.java)

}