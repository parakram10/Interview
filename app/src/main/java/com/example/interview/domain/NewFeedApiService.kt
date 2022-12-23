package com.example.interview.domain

import com.example.interview.data.NewsFeeds
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewFeedApiService {

    @GET("v2/top-headlines")
    fun fetchFeedData(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int
    ): Single<NewsFeeds>
}