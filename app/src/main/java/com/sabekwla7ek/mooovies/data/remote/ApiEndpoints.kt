package com.sabekwla7ek.mooovies.data.remote

import com.sabekwla7ek.mooovies.model.ResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yahia Allam on 21/05/2018
 */
interface ApiEndpoints {

    @GET("popular/")
    fun getMovies(@Query("sort_by") sortBy: String? = null): Single<ResponseModel>

}