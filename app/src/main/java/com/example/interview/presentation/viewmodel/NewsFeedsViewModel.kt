package com.example.interview.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interview.data.NewsFeeds
import com.example.interview.domain.NewsFeedRepo
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class NewsFeedsViewModel(private val repo: NewsFeedRepo) : ViewModel() {

    private val newsFeeds: MutableLiveData<Response<NewsFeeds>> =
        MutableLiveData<Response<NewsFeeds>>()

    val feedsData: LiveData<Response<NewsFeeds>> = newsFeeds

    fun fetchNewsFeed(page: Int) {
        repo.fetchFeedData("us", "business", page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<NewsFeeds> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: NewsFeeds) {
                    newsFeeds.postValue(Response.success(t))
                }

                override fun onError(e: Throwable) {
                    newsFeeds.postValue(Response.error(400, ResponseBody.create(null, "")))
                }

            })
    }

}