package com.furkanmulayim.shopper.ui.panel

import android.app.Application
import com.furkanmulayim.shopper.repository.ProductRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PanelViewModel @Inject constructor(
    application: Application,
    private val cpr: ProductRepository
) : BaseViewModel(application) {


    fun createProduct(
        id: Int,
        isim: String,
        aciklama: String,
        image: String,
        gecerliFiyat: String,
        oncekiFiyat: String,
        indirimAciklama: String,
        kategori: String,
        numara: String,
        renk: String,
        renkSecenek: Int,
        satilanAdet: Int,
        ilgiliUrunId: String,
        hastags: String,
        isAktif: Boolean,
        isKargoUcret: Boolean,
        isReelsActive: Boolean,
        isSiparisAlim: Boolean,
        isUreticiSecimi: Boolean,
        isYeni: Boolean,
    ) {
        cpr.saveData(
            id = id,
            isim = isim,
            aciklama = aciklama,
            image = image,
            gecerliFiyat = gecerliFiyat,
            oncekiFiyat = oncekiFiyat,
            indirimAciklama = indirimAciklama,
            kategori = kategori,
            numara = numara,
            renk = renk,
            renkSecenek = renkSecenek,
            satilanAdet = satilanAdet,
            ilgiliUrunId = ilgiliUrunId,
            hastags = hastags,
            isAktif = isAktif,
            isKargoUcret = isKargoUcret,
            isReelsActive = isReelsActive,
            isSiparisAlim = isSiparisAlim,
            isUreticiSecimi = isUreticiSecimi,
            isYeni = isYeni
        )
    }
}