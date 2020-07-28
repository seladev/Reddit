package com.sela.reddit.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Reddit response - the response that return when getting reddit
 * parse from json
 */
data class RedditResponse (
    @SerializedName("data") val data : RedditData
)

data class RedditData (
    @SerializedName("children") val children : List<Child>,
    @SerializedName("after") val after : String,
    @SerializedName("before") val before : String
)

data class Child (
    @SerializedName("data") val childData: RedditChildData
)

/**
 * Reddit child data - This is the object that contain the data to show
 */
@Parcelize
data class RedditChildData (
    @SerializedName("id") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("thumbnail") val thumbnail : String,
    @SerializedName("permalink") val permalink : String,
    @SerializedName("url") val url : String,
    @SerializedName("num_comments") val numComments : String
) : Parcelable

