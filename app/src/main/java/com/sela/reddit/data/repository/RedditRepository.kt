package com.sela.reddit.data.repository

import com.sela.reddit.data.api.RedditAPI
import com.sela.reddit.data.api.RedditChildData
import com.sela.reddit.data.api.RedditRetrofitService

/**
 * Created by seladev
 */
object RedditRepository : RedditInterface{

    private val redditAPI =  RedditRetrofitService.buildService(RedditAPI::class.java)


    override suspend fun getMainReddit() : List<RedditChildData>{
        return redditAPI.getMainRedditPage().data.children.map { it.childData }
    }

    override suspend fun getRedditPost(postLink: String): RedditChildData {
        return redditAPI.getRedditPost(postLink).first().data.children.first().childData
    }
}