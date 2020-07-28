package com.sela.reddit.ui.mainreddit

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
 * MainRedditViewModel - view model for all the logic for main reddit page
 */
class MainRedditViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _postList = MutableLiveData<List<RedditChildData>>()
    val postList : LiveData<List<RedditChildData>>
        get() = _postList

    private var _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
        get() = _loading


    init {
        refreshMainReddit()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun refreshMainReddit(){

        uiScope.launch {
            try {
                _loading.value = true
                val mainRedditList = RedditRepository.getMainReddit()
                _postList.value = mainRedditList
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