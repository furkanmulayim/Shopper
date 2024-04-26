package com.furkanmulayim.modamula.ui.welcome.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentFirstTabBinding
import com.furkanmulayim.modamula.ui.welcome.WelcomeViewModel

class FirstTabFragment : BaseFragment<FragmentFirstTabBinding, WelcomeViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentFirstTabBinding {
        return FragmentFirstTabBinding.inflate(inflater, container, false)
    }


}