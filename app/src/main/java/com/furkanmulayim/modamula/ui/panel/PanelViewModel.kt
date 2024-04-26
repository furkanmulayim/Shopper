package com.furkanmulayim.modamula.ui.panel

import android.app.Application
import com.furkanmulayim.modamula.repository.ProductRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PanelViewModel @Inject constructor(
    application: Application,
    private val cpr: ProductRepository
) : BaseViewModel(application) {


    fun createProduct(
        description: String, documentId: String, currentPrice: String,
        hastags: String, id: Int, relatedProductId: String,
        image: String, discDesc: String, active: Int,
        cargoPrice: Int, producerSelect: Int, new: Int, name: String,
        category: String, number: String, beforePrice: String,
        color: String, unitSold: Int, compatibleSize: String
    ) {
        cpr.saveData(
            description = description, documentId = documentId, currentPrice = currentPrice,
            hastags = hastags, id = id, relatedProductId = relatedProductId,
            image = image, discDesc = discDesc, active = active, cargoPrice = cargoPrice,
            producerSelect = producerSelect, new = new, name = name,
            category = category, number = number, beforePrice = beforePrice,
            color = color, unitSold = unitSold, compatibleSize = compatibleSize
        )
    }
}