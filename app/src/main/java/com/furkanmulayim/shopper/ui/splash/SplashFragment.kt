package com.furkanmulayim.shopper.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel.isAppOpened.observe(viewLifecycleOwner) {
            it.let {
                if (it) {
                    val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                    navigateTo(
                        actionId = action.actionId,
                        popUpToId = R.id.splashFragment,
                        inclusive = true
                    )
                } else {
                    val action = SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
                    navigateTo(action.actionId, popUpToId = R.id.splashFragment, inclusive = true)
                }
            }
        }
    }


}