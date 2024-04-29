package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.datasource.ProductDataSource
import com.furkanmulayim.modamula.data.model.Product

class ProductRepository(private var productDataSource: ProductDataSource) {

    fun getData(): MutableLiveData<List<Product>> = productDataSource.getData()
    fun searchById(id: Int): MutableLiveData<Product?> = productDataSource.searchById(id)
    fun search(searchText: String): MutableLiveData<List<Product>> =
        productDataSource.search(searchText)

    fun saveData(
        description: String, documentId: String, currentPrice: String,
        hastags: String, id: Int, relatedProductId: String,
        image: String, discDesc: String, active: Int,
        cargoPrice: Int, producerSelect: Int, new: Int, name: String,
        category: String, number: String, beforePrice: String,
        color: String, unitSold: Int, compatibleSize: String
    ) {
        productDataSource.saveData(
            description = description, documentId = documentId, currentPrice = currentPrice,
            hastags = hastags, id = id, relatedProductId = relatedProductId,
            image = image, discDesc = discDesc, active = active, cargoPrice = cargoPrice,
            producerSelect = producerSelect, new = new, name = name,
            category = category, number = number, beforePrice = beforePrice,
            color = color, unitSold = unitSold, compatibleSize = compatibleSize
        )
    }

}