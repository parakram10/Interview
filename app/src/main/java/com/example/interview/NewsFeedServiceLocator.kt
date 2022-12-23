package com.example.interview

import com.example.interview.data.NewsFeedRepoImpl
import com.example.interview.domain.NewFeedApiService
import com.example.interview.domain.NewsFeedRepo
import com.example.interview.domain.RetrofitHelper

object NewsFeedServiceLocator {
    fun provideRepo(): NewsFeedRepo {
        val apiService = RetrofitHelper.getInstance().create(NewFeedApiService::class.java)
        NewsFeedRepoImpl(apiService)
        return NewsFeedRepoImpl(apiService)
    }
}