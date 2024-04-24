package com.furkanmulayim.shopper.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentShopBinding
import com.furkanmulayim.shopper.utils.viewGone
import com.furkanmulayim.shopper.utils.viewMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding, ShopViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentShopBinding {
        return FragmentShopBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetup(); observeData()
    }

    private fun initSetup() {
        viewGone(binding.toolBar.toolbarStart)
        viewGone(binding.toolBar.toolbarEnd)
        binding.toolBar.toolbarTitle.text = getString(R.string.shop)
    }

    private fun observeData() {
        viewModel.billing.observe(viewLifecycleOwner) {
            it?.let { bill ->
                viewMessage(mcontext, bill[0].nameSurname.toString())
            }
        }
    }
}