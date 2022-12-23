package com.example.interview.domain

import com.example.interview.data.NewsFeeds
import io.reactivex.Single

interface NewsFeedRepo {
    fun fetchFeedData(
        country: String,
        category: String,
        page: Int
    ): Single<NewsFeeds>
}