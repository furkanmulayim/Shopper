package com.furkanmulayim.modamula.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.model.Notifications
import com.furkanmulayim.modamula.databinding.FragmentNotificationBinding
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>() {

    private lateinit var adapter: NotificationsAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotifications(); initSetup(); observeNotify(); initClickListeners()
    }

    private fun initSetup() {
        with(binding.toolBar) {
            viewVisible(toolbarEnd)
            viewVisible(toolbarBackCL)
            toolbarTitle.text = getString(R.string.notification)
        }
    }

    private fun initAdapter(notify: ArrayList<Notifications>) {
        adapter = NotificationsAdapter(notify)
        binding.notifyRcyc.adapter = adapter
        binding.notifyRcyc.layoutManager = LinearLayoutManager(mcontext)
    }

    private fun observeNotify() {
        viewModel.notify.observe(viewLifecycleOwner) {
            if (it != null) {
                initAdapter(it as ArrayList<Notifications>)
            }
        }
    }

    private fun initClickListeners() {
        binding.toolBar.toolbarEnd.onSingleClickListener {
            onBackPressed()
        }
    }
}