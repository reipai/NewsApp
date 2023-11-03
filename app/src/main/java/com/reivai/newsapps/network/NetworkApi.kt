package com.reivai.newsapps.network

import com.reivai.newsapps.reponse.MainResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("apiKey") apiKey:String): Observable<MainResponse>
}