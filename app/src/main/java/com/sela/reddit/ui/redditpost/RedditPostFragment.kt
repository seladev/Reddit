package com.sela.reddit.ui.redditpost

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.sela.reddit.R
import com.sela.reddit.ui.base.BaseFragment
import com.sela.reddit.utils.logDebug
import kotlinx.android.synthetic.main.fragment_reddit_post.*

/**
 * RedditPostFragment - Fragment for reddit post
 */
class RedditPostFragment : BaseFragment() {

    override var resourceLayout: Int = R.layout.fragment_reddit_post

    private lateinit var viewModel: RedditPostViewModel
    private lateinit var viewModelFactory: RedditPostViewModelFactory

    companion object{
        const val REDDIT_POST_URL = "reddit_post_url"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val redditPosturl = arguments?.getString(REDDIT_POST_URL)
        logDebug("redditChildData = $redditPosturl")

        viewModelFactory = RedditPostViewModelFactory(redditPosturl)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RedditPostViewModel::class.java)

        initObservers()
    }

    private fun initObservers() {

        viewModel.redditPost.observe(viewLifecycleOwner, Observer {
            reddit_post_title.text = it.title
            val commentText =  "${getString(R.string.number_of_comments)}: ${it.numComments}"
            reddit_post_number_of_comments.text = commentText
            reddit_post_image.load(it.thumbnail)

        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it) loading_indicator.show() else loading_indicator.hide()
        })
    }
}