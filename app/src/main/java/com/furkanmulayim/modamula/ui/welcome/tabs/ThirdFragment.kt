package com.furkanmulayim.modamula.ui.welcome.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentThirdBinding
import com.furkanmulayim.modamula.ui.welcome.WelcomeFragmentDirections
import com.furkanmulayim.modamula.ui.welcome.WelcomeViewModel
import com.furkanmulayim.modamula.utils.onSingleClickListener

class ThirdFragment : BaseFragment<FragmentThirdBinding, WelcomeViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentThirdBinding {
        return FragmentThirdBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }


    private fun clickListener() {
        binding.acceptButton.onSingleClickListener {
            val act = WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
            navigateTo(act.actionId, popUpToId = R.id.welcomeFragment, inclusive = true)
        }
    }

}