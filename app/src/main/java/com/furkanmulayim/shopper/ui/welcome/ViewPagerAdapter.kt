package com.furkanmulayim.shopper.ui.welcome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.furkanmulayim.shopper.ui.welcome.tabs.FirstTabFragment
import com.furkanmulayim.shopper.ui.welcome.tabs.SecondTabFragment
import com.furkanmulayim.shopper.ui.welcome.tabs.ThirdFragment


class ViewPagerAdapter(frag: Fragment) : FragmentStateAdapter(frag) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstTabFragment()
            1 -> SecondTabFragment()
            2 -> ThirdFragment()
            else -> throw IllegalStateException("Beklenmeyen Fragment Geldi $position")
        }

    }

}