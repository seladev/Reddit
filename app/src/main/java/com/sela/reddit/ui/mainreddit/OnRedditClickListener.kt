package com.sela.reddit.ui.mainreddit

import com.sela.reddit.data.api.RedditChildData

/**
 * OnRedditClickListener - listener when user click on reddit item
 */
interface OnRedditClickListener {
    fun onRedditClick(reddit:RedditChildData)
}