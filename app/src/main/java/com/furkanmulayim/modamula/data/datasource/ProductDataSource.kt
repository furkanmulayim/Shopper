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
                        if (product.name?.lowercase()?.contains(searchText.lowercase()) == true) {
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
        description: String, documentId: String, currentPrice: String,
        hastags: String, id: Int, relatedProductId: String,
        image: String, discDesc: String, active: Int,
        cargoPrice: Int, producerSelect: Int, new: Int, name: String,
        category: String, number: String, beforePrice: String,
        color: String, unitSold: Int, compatibleSize: String
    ) {
        val newProduct = Product(
            description = description, documentId = documentId, currentPrice = currentPrice,
            hastags = hastags, id = id, relatedProductId = relatedProductId,
            image = image, discDesc = discDesc, active = active, cargoPrice = cargoPrice,
            producerSelect = producerSelect, new = new, name = name,
            category = category, number = number, beforePrice = beforePrice,
            color = color, unitSold = unitSold, compatibleSize = compatibleSize
        )
        collectionProduct.document().set(newProduct)
    }
}