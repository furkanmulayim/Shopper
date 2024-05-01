package com.furkanmulayim.modamula.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Product
import com.google.firebase.firestore.CollectionReference

class ProductDataSource(
    private val collectionProduct: CollectionReference,
) {


    private var productList = MutableLiveData<List<Product>>()
    private var denek = MutableLiveData<List<Product>>()

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

    fun searchCategText(categ: String): MutableLiveData<List<Product>> {
        collectionProduct.addSnapshotListener { value, error ->
            if (value != null) {
                println("LOGDF: pds-> girdi")
                val list = ArrayList<Product>()
                for (i in value.documents) {
                    val product = i.toObject(Product::class.java)
                    if (product != null) {
                        if (categ == product.category) {
                            println("LOGDF: pds-> eklendi")
                            list.add(product)
                        }
                    }
                }
                denek.value = list
            } else
                println("LOGDF: pds-> nıoo")
        }
        return denek
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