package com.sela.reddit.data.api

import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Reddit Api - for get reddit post from reddit api
 */
interface RedditAPI {

    /**
     * get reddit main page
     */
    @GET(".json")
    suspend fun getMainRedditPage(): RedditResponse

    /**
     * get reddit post by post path - retuen list of RedditResponse
     */
    @GET("{path}.json")
    suspend fun getRedditPost(@Path("path", encoded = true) path:String): List<RedditResponse>
}