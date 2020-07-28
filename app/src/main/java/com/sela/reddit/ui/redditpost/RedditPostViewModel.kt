package com.sela.reddit.ui.redditpost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sela.reddit.data.api.RedditChildData
import com.sela.reddit.data.repository.RedditRepository
import com.sela.reddit.utils.logDebug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * RedditPostViewModel - view model for all the logic for reddit post
 */
class RedditPostViewModel(private val redditPostUrl: String?) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _redditPost = MutableLiveData<RedditChildData>()
    val redditPost : LiveData<RedditChildData>
        get() = _redditPost

    private var _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
        get() = _loading


    init {
        logDebug("redditPostUrl = $redditPostUrl")
        loadRedditPost()

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun loadRedditPost(){
        redditPostUrl?.let {
            uiScope.launch {
                try {
                    _loading.value = true
                    val redditPost = RedditRepository.getRedditPost(it)
                    _redditPost.value = redditPost

                }catch (e:Exception){
                    logDebug("Error  = ${e.localizedMessage}")
                    e.printStackTrace()
                }
                finally {
                    _loading.value = false
                }
            }
        }
    }
}