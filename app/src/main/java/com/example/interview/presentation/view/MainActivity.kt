package com.example.interview.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.interview.NewsFeedServiceLocator
import com.example.interview.databinding.ActivityMainBinding
import com.example.interview.presentation.NewsFeedAdapter
import com.example.interview.presentation.viewmodel.NewsFeedsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel = NewsFeedsViewModel(NewsFeedServiceLocator.provideRepo())
    private val adapter = NewsFeedAdapter(this)

    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNews.adapter = adapter

        setUpObserver()

        viewModel.fetchNewsFeed(count)

        binding.rvNews.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                val itemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastVisible == itemCount - 1) {
                    count++
                    viewModel.fetchNewsFeed(count)
                }
            }
        })
    }

    private fun setUpObserver() {
        viewModel.feedsData.observe(this) {
            if (it.isSuccessful) {
                val data = it.body()?.articles
                if (data != null) {
                    adapter.setNewsFeedList(data, count)
                } else {
                    Toast.makeText(this, "Feeds are empty", Toast.LENGTH_SHORT).show()
                }

            } else {

            }
        }
    }
}