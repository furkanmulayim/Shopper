package com.furkanmulayim.modamula.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentShopBinding
import com.furkanmulayim.modamula.utils.animFast
import com.furkanmulayim.modamula.utils.animSecond
import com.furkanmulayim.modamula.utils.getBackgrounDrawavle
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding, ShopViewModel>() {

    private var currentLayout: Boolean = true
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentShopBinding {
        return FragmentShopBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBillData(); initUI(); observeData(); initClickListeners()
    }

    private fun observeData() {
        viewModel.billing.observe(viewLifecycleOwner) {
            it.let { bill ->
                if (!bill?.nameSurname.isNullOrEmpty()) {
                    setBankDescription(bill?.bankName, bill?.nameSurname, bill?.iban)
                    setSocial(bill?.inTouch, bill?.instagramAccount, bill?.dolapAccount)
                }
            }
        }
    }


    private fun initUI() {
        binding.apply {
            //animFirst(layoutProductHow)
            with(toolBar) {
                viewGone(toolbarStart)
                viewGone(toolbarEnd)
                toolbarTitle.text = getString(R.string.shop)
            }
            with(descButton) {
                desc.text = getString(R.string.desc_button)
                descImage.background = getBackgrounDrawavle(mcontext, R.drawable.desc_button)
            }
            with(descWhatsapp) {
                desc.text = getString(R.string.desc_wp)
                descImage.background = getBackgrounDrawavle(mcontext, R.drawable.desc_wp)
            }
            with(descPay) {
                desc.text = getString(R.string.desc_pay)
                descImage.background = getBackgrounDrawavle(mcontext, R.drawable.desc_pay)
            }
            with(descSendSheet) {
                desc.text = getString(R.string.desc_sheet)
                descImage.background = getBackgrounDrawavle(mcontext, R.drawable.desc_sheet)
            }
            with(descOk) {
                desc.text = getString(R.string.desc_ok)
                descImage.background = getBackgrounDrawavle(mcontext, R.drawable.desc_cargo)
            }
        }
    }


    private fun setBankDescription(bankNameText: String?, personName: String?, iban: String?) {
        binding.apply {
            viewGone(bankName.copyButton)
            bankName.description.text = getString(R.string.b_name)
            bankName.desc.text = bankNameText
            accountIban.desc.text = iban
            accountName.desc.text = personName
            accountIban.description.text = getString(R.string.b_iban)
            accountName.description.text = getString(R.string.b_acc_pers)
            animSecond(layoutBuyDescription)
        }
    }

    private fun initClickListeners() {
        with(binding) {
            eftButton.onSingleClickListener {
                if (!currentLayout) {
                    viewGone(socialLayout)
                    animFast(eftLayout)
                    socialButton.background = null
                    eftButton.background = getBackgrounDrawavle(mcontext, R.drawable.shop_bar_back)
                    currentLayout = true
                }
            }

            socialButton.onSingleClickListener {
                if (currentLayout) {
                    viewGone(eftLayout)
                    animFast(socialLayout)
                    eftButton.background = null
                    socialButton.background =
                        getBackgrounDrawavle(mcontext, R.drawable.shop_bar_back)
                    currentLayout = false
                }
            }

            wp.button.onSingleClickListener {
                viewMessage(mcontext, "Whatsapp")
            }

            insta.button.onSingleClickListener {
                viewMessage(mcontext, "Instagram")
            }

            trendyolDolap.button.onSingleClickListener {
                viewMessage(mcontext, "Dolap")
            }

            accountName.copyButton.onSingleClickListener {
                viewMessage(mcontext, "Kopyala")
            }

            accountIban.copyButton.onSingleClickListener {

            }
        }
    }

    private fun setSocial(intouch: String?, instagram: String?, dolap: String?) {
        with(binding) {
            wp.contactImage.foreground = getBackgrounDrawavle(mcontext, R.drawable.whatsapp)
            wp.contactName.text = getString(R.string.in_touch)
            wp.contactSubName.text = intouch

            insta.contactImage.foreground = getBackgrounDrawavle(mcontext, R.drawable.instagram)
            insta.contactName.text = getString(R.string.instagramAccount)
            insta.contactSubName.text = instagram

            trendyolDolap.contactImage.foreground = getBackgrounDrawavle(mcontext, R.drawable.dolap)
            trendyolDolap.contactName.text = getString(R.string.dolapAccount)
            trendyolDolap.contactSubName.text = dolap
        }
    }
}