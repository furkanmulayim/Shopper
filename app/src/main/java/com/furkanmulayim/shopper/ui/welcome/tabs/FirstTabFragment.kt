package com.furkanmulayim.shopper.ui.welcome.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentFirstTabBinding
import com.furkanmulayim.shopper.ui.welcome.WelcomeViewModel

class FirstTabFragment : BaseFragment<FragmentFirstTabBinding, WelcomeViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentFirstTabBinding {
        return FragmentFirstTabBinding.inflate(inflater, container, false)
    }


}