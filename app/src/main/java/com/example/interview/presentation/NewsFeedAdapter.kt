package com.example.interview.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.interview.data.NewsFeeds
import com.example.interview.databinding.LayoutFeedBinding

class NewsFeedAdapter(private val context: Context) :
    RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {

    private var newsFeedList: List<NewsFeeds.Articles>? = null
    private var count: Int = 1

    class NewsFeedViewHolder(binding: LayoutFeedBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivImage
        val title = binding.tvNewsTitle
        val desc = binding.tvDesc
        val author = binding.tvAuthor
        val itemNumber = binding.tvItemNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val binding = LayoutFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        holder.apply {
            val data = newsFeedList?.get(position)
            Glide.with(context).load(data?.url).into(image)

            title.text = data?.title
            desc.text = data?.description
            author.text = data?.author
            itemNumber.text = ((position + 1) * count).toString()
        }
    }

    override fun getItemCount(): Int = newsFeedList?.size ?: 0

    fun setNewsFeedList(list: List<NewsFeeds.Articles>, count: Int) {
        this.newsFeedList = list
        this.count = count
        notifyDataSetChanged()
    }

}