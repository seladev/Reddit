package com.sela.reddit.ui.mainreddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sela.reddit.R
import com.sela.reddit.data.api.RedditChildData

/**
 * MainRedditAdapter - Adapter for show the reddit items
 */
class MainRedditAdapter : ListAdapter<RedditChildData, MainRedditAdapter.MainRedditViewHolder>(DiffCallback()) {

    var onRedditClickListener: OnRedditClickListener? = null

    class MainRedditViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView){
        private val mainRedditTitle = itemView.findViewById<TextView>(R.id.main_reddit_title)
        private val mainRedditImage = itemView.findViewById<ImageView>(R.id.main_reddit_image)

        fun bind(item: RedditChildData, clickListener: OnRedditClickListener?) {
            mainRedditTitle.text = item.title
            mainRedditImage.load(item.thumbnail)
            itemView.setOnClickListener { clickListener?.onRedditClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRedditViewHolder {
        return MainRedditViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.main_reddit_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainRedditViewHolder, position: Int) {
        holder.bind(getItem(position), onRedditClickListener)
    }

}

class DiffCallback : DiffUtil.ItemCallback<RedditChildData>() {
    override fun areItemsTheSame(oldItem: RedditChildData, newItem: RedditChildData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RedditChildData, newItem: RedditChildData): Boolean {
        return oldItem == newItem
    }
}