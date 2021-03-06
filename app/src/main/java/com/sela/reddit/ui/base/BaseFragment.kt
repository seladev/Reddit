package com.sela.reddit.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sela.reddit.R
import com.sela.reddit.utils.logView

/**
 * BaseFragment - for all fragments in app
 */
abstract class BaseFragment:Fragment() {

    /**
     * resourceLayout to inflate
     */
    abstract var resourceLayout: Int

    /**
     * Screen title
     */
    open var titleResource = R.string.app_name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logView("onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logView("onCreateView")
        return inflater.inflate(resourceLayout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = getString(titleResource)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        logView("onDestroyView")
    }
}