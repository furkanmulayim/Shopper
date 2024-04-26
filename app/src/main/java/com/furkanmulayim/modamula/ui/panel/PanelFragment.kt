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
                            isim = addIsim.text.toString(),
                            aciklama = addAciklama.text.toString(),
                            image = addImage.text.toString(),
                            gecerliFiyat = addGuncelFiyat.text.toString(),
                            oncekiFiyat = addOnceki.text.toString(),
                            indirimAciklama = addIndirimAciklama.text.toString(),
                            kategori = addKategori.text.toString(),
                            numara = addNumara.text.toString(),
                            renk = addRenk.text.toString(),
                            uyumluBedenler = uyumluBedenler,
                            satilanAdet = satilanAdet,
                            ilgiliUrunId = addIlgiliUrunId.text.toString(),
                            hastags = addHastag.text.toString(),
                            isAktif = addIsAktif.text.toString().toInt(),
                            isKargoUcret = addIsKargoUcret.text.toString().toInt(),
                            isUreticiSecimi = addIsUreticiSecimi.text.toString().toInt(),
                            isYeni = addIsYeni.text.toString().toInt()
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
