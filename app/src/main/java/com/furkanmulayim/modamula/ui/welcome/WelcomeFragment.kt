package com.furkanmulayim.modamula.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.enums.WelcomeTabsItem
import com.furkanmulayim.modamula.databinding.FragmentWelcomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, WelcomeViewModel>() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentWelcomeBinding {
        return FragmentWelcomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(0, false)
        tabButtonsSet()
    }

    private fun tabButtonsSet() {
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->

            when (pos) {
                WelcomeTabsItem.FIRST.ordinal -> {
                    tab.text = WelcomeTabsItem.FIRST.id
                }

                WelcomeTabsItem.SECOND.ordinal -> {
                    tab.text = WelcomeTabsItem.SECOND.id
                }

                WelcomeTabsItem.THIRD.ordinal -> {
                    tab.text = WelcomeTabsItem.THIRD.id
                }
            }


        }.attach()
    }
}