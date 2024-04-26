package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.datasource.ProductDataSource
import com.furkanmulayim.modamula.data.model.Product

class ProductRepository(private var pds: ProductDataSource) {

    fun getData(): MutableLiveData<List<Product>> = pds.getData()
    fun searchById(id: Int): MutableLiveData<Product?> = pds.searchById(id)
    fun search(searchText: String): MutableLiveData<List<Product>> = pds.search(searchText)

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
        uyumluBedenler: String,
        satilanAdet: Int,
        ilgiliUrunId: String,
        hastags: String,
        isAktif: Int,
        isKargoUcret: Int,
        isUreticiSecimi: Int,
        isYeni: Int,
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
            uyumluBedenler = uyumluBedenler,
            satilanAdet = satilanAdet,
            ilgiliUrunId = ilgiliUrunId,
            hastags = hastags,
            isAktif = isAktif,
            isKargoUcret = isKargoUcret,
            isUreticiSecimi = isUreticiSecimi,
            isYeni = isYeni
        )
    }

}