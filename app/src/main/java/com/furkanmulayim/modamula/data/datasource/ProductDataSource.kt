package com.furkanmulayim.modamula.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Product
import com.google.firebase.firestore.CollectionReference

class ProductDataSource(
    private val collectionProduct: CollectionReference,
) {


    private var productList = MutableLiveData<List<Product>>()
    private var searchList = MutableLiveData<List<Product>>()
    private var searchById = MutableLiveData<Product>()

    fun getData(): MutableLiveData<List<Product>> {
        collectionProduct.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Product>()
                for (i in value.documents) {
                    val product = i.toObject(Product::class.java)
                    if (product != null) {
                        product.documentId = i.id
                        list.add(product)
                    }
                }
                productList.value = list
            }
        }
        return productList
    }

    fun searchById(id: Int): MutableLiveData<Product?> {
        val searchResult = MutableLiveData<Product?>()

        collectionProduct
            .whereEqualTo("id", id)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val product = documents.documents[0].toObject(Product::class.java)
                    product?.documentId = documents.documents[0].id
                    searchResult.postValue(product)
                } else {
                    // Belirli bir ID ile eşleşen belge bulunamadı.
                    // Uygun bir şekilde işlem yapılabilir, örneğin null döndürmek veya bir hata mesajı göstermek.
                }
            }
            .addOnFailureListener { e ->
                // Hata oluştuğunda uygun şekilde işlem yapılabilir.
                throw e // Hata durumunda istisna fırlatılabilir.
            }

        return searchResult
    }


    fun search(searchText: String): MutableLiveData<List<Product>> {
        collectionProduct.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Product>()
                for (i in value.documents) {
                    val product = i.toObject(Product::class.java)
                    if (product != null) {
                        if (product.isim?.lowercase()?.contains(searchText.lowercase()) == true) {
                            product.documentId = i.id
                            list.add(product)
                        }
                    }
                }
                searchList.value = list
            }
        }
        return searchList
    }

    fun saveData(
        aciklama: String,
        gecerliFiyat: String,
        id: Int,
        image: String,
        isAktif: Int,
        isKargoUcret: Int,
        indirimAciklama: String,
        ilgiliUrunId: String,
        hastags: String,
        isUreticiSecimi: Int,
        isYeni: Int,
        isim: String,
        kategori: String,
        numara: String,
        oncekiFiyat: String,
        renk: String,
        uyumluBedenler: String,
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
            uyumluBedenler = uyumluBedenler,
            satilanAdet = satilanAdet,
            ilgiliUrunId = ilgiliUrunId,
            hastags = hastags,
            isAktif = isAktif,
            isKargoUcret = isKargoUcret,
            isUreticiSecimi = isUreticiSecimi,
            isYeni = isYeni
        )
        collectionProduct.document().set(newProduct)
    }
}