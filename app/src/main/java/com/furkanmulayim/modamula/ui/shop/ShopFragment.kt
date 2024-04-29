package com.furkanmulayim.modamula.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentShopBinding
import com.furkanmulayim.modamula.utils.viewGone
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
        with(binding) {
            viewGone(toolBar.toolbarStart)
            viewGone(toolBar.toolbarEnd)
            toolBar.toolbarTitle.text = getString(R.string.shop)
        }
    }

    private fun observeData() {
        viewModel.billing.observe(viewLifecycleOwner) {
            it?.let { bill ->
                println("LOGDF: " + bill.bankName)
                if (!viewModel.isSqliteData && bill != null) {
                    viewModel.setSqliteData(bill)
                }
                if (bill != null) {
                    with(binding) {
                        isimSoyisim.text = bill.nameSurname.toString()
                        iletisim.text = bill.inTouch.toString()
                    }
                }
            }
        }
    }
}