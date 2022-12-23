package com.example.interview.data

import com.example.interview.domain.NewFeedApiService
import com.example.interview.domain.NewsFeedRepo
import io.reactivex.Single

class NewsFeedRepoImpl(private val apiService: NewFeedApiService) : NewsFeedRepo {
    override fun fetchFeedData(
        country: String,
        category: String,
        page: Int
    ): Single<NewsFeeds> = apiService.fetchFeedData(
        country,
        category,
        "f09596d2d9ad42e3be7e4b166c93cd13",
        page
    )
}