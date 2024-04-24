package com.furkanmulayim.shopper.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.shopper.data.datasource.ProductDataSource
import com.furkanmulayim.shopper.data.model.Product

class ProductRepository(private var pds: ProductDataSource) {

    fun getData(): MutableLiveData<List<Product>> = pds.getData()

    fun searchData(searchText: String): MutableLiveData<List<Product>> = pds.searchData(searchText)

    fun saveData(
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
        pds.saveData(
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