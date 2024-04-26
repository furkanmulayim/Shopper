package com.furkanmulayim.modamula.ui.welcome.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentSecondTabBinding
import com.furkanmulayim.modamula.ui.welcome.WelcomeViewModel

class SecondTabFragment : BaseFragment<FragmentSecondTabBinding, WelcomeViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSecondTabBinding {
        return FragmentSecondTabBinding.inflate(inflater, container, false)
    }

}