package com.sela.reddit.data.repository

import com.sela.reddit.data.api.RedditChildData

/**
 * Created by seladev
 */
interface RedditInterface {

    /**
     * get reddit main page
     */
    suspend fun getMainReddit() : List<RedditChildData>

    /**
     * get reddit main page
     */
    suspend fun getRedditPost(postLink: String) : RedditChildData

}