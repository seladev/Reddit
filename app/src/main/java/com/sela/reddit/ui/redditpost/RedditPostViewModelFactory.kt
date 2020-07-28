package com.sela.reddit.ui.redditpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * RedditPostViewModelFactory - for creating  RedditPostViewModel
 */
class RedditPostViewModelFactory(private val redditPostUrl: String?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedditPostViewModel::class.java)) {
            return RedditPostViewModel(redditPostUrl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")    }
}