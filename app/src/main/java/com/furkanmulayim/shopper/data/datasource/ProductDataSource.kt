package com.furkanmulayim.shopper.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.shopper.data.model.Product
import com.google.firebase.firestore.CollectionReference

class ProductDataSource(
    private val collectionProduct: CollectionReference,
) {


    private var productList = MutableLiveData<List<Product>>()

    fun getData(): MutableLiveData<List<Product>> {
        collectionProduct.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Product>()
                for (i in value.documents) {
                    val product = i.toObject(Product::class.java)
                    if (product != null) {
                        list.add(product)
                    }
                }
                productList.value = list
            }
        }
        return productList
    }

    fun searchData(searchText: String): MutableLiveData<List<Product>> {
        return MutableLiveData<List<Product>>()
    }

    fun saveData(
        aciklama: String,
        gecerliFiyat: String,
        id: Int,
        image: String,
        isAktif: Boolean,
        isKargoUcret: Boolean,
        isReelsActive: Boolean,
        isSiparisAlim: Boolean,
        indirimAciklama: String,
        ilgiliUrunId: String,
        hastags: String,
        isUreticiSecimi: Boolean,
        isYeni: Boolean,
        isim: String,
        kategori: String,
        numara: String,
        oncekiFiyat: String,
        renk: String,
        renkSecenek: Int,
        satilanAdet: Int
    ) {
        val newProduct = Product(
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
        collectionProduct.document().set(newProduct)
    }
}