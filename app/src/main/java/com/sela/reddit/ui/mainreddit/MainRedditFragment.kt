package com.sela.reddit.ui.mainreddit

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sela.reddit.R
import com.sela.reddit.data.api.RedditChildData
import com.sela.reddit.ui.base.BaseFragment
import com.sela.reddit.ui.redditpost.RedditPostFragment.Companion.REDDIT_POST_URL
import kotlinx.android.synthetic.main.fragment_main_reddit.*

/**
 * MainRedditFragment - Fragment for main reddit page
 */
class MainRedditFragment : BaseFragment() {

    override var resourceLayout: Int = R.layout.fragment_main_reddit

    private val viewModel: MainRedditViewModel by viewModels()
    private lateinit var mainRedditAdapter: MainRedditAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainRedditAdapter = MainRedditAdapter()
        mainRedditAdapter.onRedditClickListener = object : OnRedditClickListener {
            override fun onRedditClick(reddit: RedditChildData) {
                val bundle = bundleOf(REDDIT_POST_URL to reddit.permalink)
                view.findNavController().navigate(R.id.action_mainRedditFragment_to_redditPostFragment, bundle)
            }
        }


        main_reddit_recycler_view.let {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            it.addItemDecoration(dividerItemDecoration)
            main_reddit_recycler_view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            main_reddit_recycler_view.adapter = this.mainRedditAdapter
        }

        initObservers()
    }

    private fun initObservers() {

        viewModel.postList.observe(viewLifecycleOwner, Observer {
            mainRedditAdapter.submitList(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it) loading_indicator.show() else loading_indicator.hide()
        })
    }

}