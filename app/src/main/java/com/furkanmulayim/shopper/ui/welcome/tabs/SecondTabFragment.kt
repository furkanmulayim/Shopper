package com.furkanmulayim.shopper.ui.welcome.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentSecondTabBinding
import com.furkanmulayim.shopper.ui.welcome.WelcomeViewModel

class SecondTabFragment : BaseFragment<FragmentSecondTabBinding, WelcomeViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSecondTabBinding {
        return FragmentSecondTabBinding.inflate(inflater, container, false)
    }

}