package com.furkanmulayim.modamula.ui.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.databinding.FragmentPanelBinding
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.viewErrorMessage
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewMessage
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PanelFragment : BaseFragment<FragmentPanelBinding, PanelViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPanelBinding {
        return FragmentPanelBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        with(binding) {
            authButton.setOnClickListener {
                // Todo burda şifre kontrol edilsin
                if (true) {
                    viewMessage(mcontext, getString(R.string.yetkili))
                    viewGone(binding.authPanel)
                    viewVisible(binding.buttonsPanel)
                } else {
                    viewErrorMessage(mcontext, getString(R.string.yetkisiz))
                    onBackPressed()
                }
            }

            createButton.onSingleClickListener {
                val id = addId.text.toString().toIntOrNull()
                val uyumluBedenler = uyumluBedenler.text.toString()
                val satilanAdet = addSatilanAdet.text.toString().toIntOrNull()

                if (id != null && satilanAdet != null) {
                    with(binding) {
                        viewModel.createProduct(
                            id = id,
                            name = addIsim.text.toString(),
                            description = addAciklama.text.toString(),
                            image = addImage.text.toString(),
                            documentId = "",
                            currentPrice = addGuncelFiyat.text.toString(),
                            beforePrice = addOnceki.text.toString(),
                            discDesc = addIndirimAciklama.text.toString(),
                            category = addKategori.text.toString(),
                            number = addNumara.text.toString(),
                            color = addRenk.text.toString(),
                            compatibleSize = uyumluBedenler,
                            unitSold = satilanAdet,
                            relatedProductId = addIlgiliUrunId.text.toString(),
                            hastags = addHastag.text.toString(),
                            active = addIsAktif.text.toString().toInt(),
                            cargoPrice = addIsKargoUcret.text.toString().toInt(),
                            producerSelect = addIsUreticiSecimi.text.toString().toInt(),
                            new = addIsYeni.text.toString().toInt()
                        )
                        viewMessage(mcontext, getString(R.string.success))
                        onBackPressed()
                    }
                } else {
                    viewErrorMessage(mcontext, getString(R.string.yetkisiz))
                }
            }
        }
    }
}
