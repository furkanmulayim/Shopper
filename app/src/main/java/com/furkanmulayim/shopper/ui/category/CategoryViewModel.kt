package com.furkanmulayim.shopper.ui.category

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.enums.CategoryName
import com.furkanmulayim.shopper.data.model.Fiyat
import com.furkanmulayim.shopper.data.model.Lojik
import com.furkanmulayim.shopper.data.model.ProductCategory
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.tarifce.base.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(app: Application, val ssh: SavedStateHandle) : BaseViewModel(app) {

    var isSearchFocused: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getBundle()
    }

    private fun getBundle() {
        viewModelScope.launch {
            var bundle = ssh.get<Boolean>("search")
            bundle?.let {
                isSearchFocused.value = it
            }
        }

    }

    val categories = arrayListOf(
        ProductCategory(
            image = R.drawable.menu_item_shopping_car,
            back = R.drawable.category_item_white,
            name = CategoryName.TUMU.id
        ),
        ProductCategory(
            image = R.drawable.menu_item_shopping_car,
            back = R.drawable.category_item_white,
            name = CategoryName.JIK.id
        ),
        ProductCategory(
            image = R.drawable.menu_item_shopping_car,
            back = R.drawable.category_item_white,
            name = CategoryName.BEBEK.id
        )
    )


    val products = arrayListOf(
        ProductItem(
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
            indirimAciklama = "",
            renk = 2,
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
                isUreticiSecimi = true,
                isKargoUcret = false,
                isReelsActive = true
            ),
            ilgiliUrunler = "Deri Ceket",
            hastag = "#deneme #deneme yeni #yeni etiketli"

        )
    )


    val products1 = arrayListOf(
        ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            indirimAciklama = "",
            renk = 2,
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
        )

    )


    val products2 = arrayListOf(
        ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            aciklama = "",
            numara = "",
            indirimAciklama = "",
            renk = 2,
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
        ),
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
        ),
        ProductItem(
            id = 1,
            image = "www.google.com",
            isim = "Trençkot",
            kategori = "",
            indirimAciklama = "",
            aciklama = "",
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
                isYeni = false,
                isKargoUcret = true,
                isSiparisAlim = true,
                isUreticiSecimi = true,
                isReelsActive = true
            ),
            ilgiliUrunler = "Deri Ceket",
            hastag = "#deneme #deneme yeni #yeni etiketli"
        )
    )

}