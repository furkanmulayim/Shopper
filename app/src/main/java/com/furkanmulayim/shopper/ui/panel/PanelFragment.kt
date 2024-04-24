package com.furkanmulayim.shopper.ui.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentPanelBinding
import com.furkanmulayim.shopper.utils.onSingleClickListener
import com.furkanmulayim.shopper.utils.viewErrorMessage
import com.furkanmulayim.shopper.utils.viewGone
import com.furkanmulayim.shopper.utils.viewMessage
import com.furkanmulayim.shopper.utils.viewVisible
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
                val isAktif = textToBoolean(addIsAktif.text.toString())
                val isKargoUcret = textToBoolean(addIsKargoUcret.text.toString())
                val isReelsActive = textToBoolean(addIsReelsActive.text.toString())
                val isSiparisAlim = textToBoolean(addIsReelsActive.text.toString())
                val isUreticiSecimi = textToBoolean(addHastag.text.toString())
                val isYeni = textToBoolean(addHastag.text.toString())

                val id = addId.text.toString().toIntOrNull()
                val renkSecenek = addRenkSecenek.text.toString().toIntOrNull()
                val satilanAdet = addSatilanAdet.text.toString().toIntOrNull()

                if (id != null && renkSecenek != null && satilanAdet != null) {
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
                            renkSecenek = renkSecenek,
                            satilanAdet = satilanAdet,
                            ilgiliUrunId = addIlgiliUrunId.text.toString(),
                            hastags = addHastag.text.toString(),
                            isAktif = isAktif,
                            isKargoUcret = isKargoUcret,
                            isReelsActive = isReelsActive,
                            isSiparisAlim = isSiparisAlim,
                            isUreticiSecimi = isUreticiSecimi,
                            isYeni = isYeni
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

    private fun textToBoolean(text: String): Boolean {
        return text == "1"
    }

}
