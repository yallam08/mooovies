package com.sabekwla7ek.mooovies.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Yahia Allam on 21/05/2018
 *
 * Interceptor for dding the mandatory key query param
 */
class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()

        val url = originalUrl.newBuilder()
                .addQueryParameter(NetworkConstants.API_KEY_KEY, NetworkConstants.API_KEY)
                .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}