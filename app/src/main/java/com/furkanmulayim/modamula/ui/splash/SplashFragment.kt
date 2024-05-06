package com.furkanmulayim.modamula.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentSplashBinding

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
                        inclusive = true,
                        animControl = false
                    )
                } else {
                    val action = SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
                    navigateTo(
                        action.actionId,
                        popUpToId = R.id.splashFragment,
                        inclusive = true,
                        animControl = false
                    )
                }
            }
        }
    }


}