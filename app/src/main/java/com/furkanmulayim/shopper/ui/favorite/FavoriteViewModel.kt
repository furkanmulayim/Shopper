package com.furkanmulayim.shopper.ui.favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import com.furkanmulayim.shopper.data.model.Fiyat
import com.furkanmulayim.shopper.data.model.Indirim
import com.furkanmulayim.shopper.data.model.Lojik
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.tarifce.base.BaseViewModel

class FavoriteViewModel(app: Application) : BaseViewModel(app) {

    val favorite = arrayListOf(
        ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            renkSecenek = listOf("Beyaz", "Bej", "Gri"),
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            indirim = Indirim(
                indirimAciklama = "Ramazan Özel",
                indirimYuzde = "%50"
            ),
            lojik = Lojik(
                isAktif = true,
                isYeni = false,
                isKargoUcret = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = listOf("Deri Ceket", "Ceket", "Kot Ceket"),
            hastag = "#deneme #deneme yeni #yeni etiketli"

        ), ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            renkSecenek = listOf("Beyaz", "Bej", "Gri"),
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            indirim = Indirim(
                indirimAciklama = "Ramazan Özel",
                indirimYuzde = "%50"
            ),
            lojik = Lojik(
                isAktif = false,
                isYeni = true,
                isKargoUcret = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = listOf("Deri Ceket", "Ceket", "Kot Ceket"),
            hastag = "#deneme #deneme yeni #yeni etiketli"

        ), ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            renkSecenek = listOf("Beyaz", "Bej", "Gri"),
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            indirim = Indirim(
                indirimAciklama = "Ramazan Özel",
                indirimYuzde = "%50"
            ),
            lojik = Lojik(
                isAktif = true,
                isYeni = true,
                isSiparisAlim = true,
                isKargoUcret = false,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = listOf("Deri Ceket", "Ceket", "Kot Ceket"),
            hastag = "#deneme #deneme yeni #yeni etiketli"

        ), ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            renk = 2,
            renkSecenek = listOf("Beyaz", "Bej", "Gri"),
            satilanAdet = 10,
            fiyat = Fiyat(
                oncekiFiyat = "300₺",
                gecerliFiyat = "150₺"
            ),
            indirim = Indirim(
                indirimAciklama = "Ramazan Özel",
                indirimYuzde = "%50"
            ),
            lojik = Lojik(
                isAktif = true,
                isYeni = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isKargoUcret = false,
                isReelsActive = true
            ),
            ilgiliUrunler = listOf("Deri Ceket", "Ceket", "Kot Ceket"),
            hastag = "#deneme #deneme yeni #yeni etiketli"

        )
    )
}