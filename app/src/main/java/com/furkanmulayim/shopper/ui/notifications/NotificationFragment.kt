package com.furkanmulayim.shopper.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentNotificationBinding
import com.furkanmulayim.shopper.utils.viewGone

class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetup()
    }

    private fun initSetup() {
        viewGone(binding.toolBar.toolbarStart)
        viewGone(binding.toolBar.toolbarEnd)
        binding.toolBar.toolbarTitle.text = getString(R.string.notification)
    }
}