package com.furkanmulayim.shopper.ui.home

import android.app.Application
import com.furkanmulayim.shopper.data.model.Fiyat
import com.furkanmulayim.shopper.data.model.Lojik
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())

    init {
        isAppAlreadyBefore()
    }

    private fun isAppAlreadyBefore() {
        //kullanıcı uygulamayı daha önceden açtıysa kaydeder
        sharedPrefs.saveWelcome()
    }


    val products = arrayListOf(
        ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            indirimAciklama = "",
            renkSecenek = "Beyaz, Bej, Gri",
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            lojik = Lojik(
                isAktif = true,
                isYeni = false,
                isKargoUcret = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = "Deri Ceket",
            hastag = "#deneme #deneme yeni #yeni etiketli"

        ), ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            indirimAciklama = "",
            renkSecenek = "Beyaz, Bej, Gri",
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            lojik = Lojik(
                isAktif = false,
                isYeni = true,
                isKargoUcret = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = "Deri Ceket",
            hastag = "#deneme #deneme yeni #yeni etiketli"

        ), ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            renkSecenek = "Beyaz, Bej, Gri",
            satilanAdet = 10,
            indirimAciklama = "",
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            lojik = Lojik(
                isAktif = true,
                isYeni = true,
                isSiparisAlim = true,
                isKargoUcret = false,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = "Deri Ceket",
            hastag = "#deneme #deneme yeni #yeni etiketli"

        ), ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            indirimAciklama = "",
            numara = "",
            renk = 2,
            renkSecenek = "Beyaz, Bej, Gri",
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            lojik = Lojik(
                isAktif = true,
                isYeni = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isKargoUcret = false,
                isReelsActive = true
            ),
            ilgiliUrunler = "Deri Ceket",
            hastag = "#deneme #deneme yeni #yeni etiketli"

        )
    )
}