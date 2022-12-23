package com.example.interview.data

import com.google.gson.annotations.SerializedName


data class NewsFeeds(
    @SerializedName("articles") val articles: List<Articles>? = null
) {
    data class Articles(
        @SerializedName("author") val author: String? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("description") val description: String? = null,
        @SerializedName("urlToImage") val url: String? = null
    )
}