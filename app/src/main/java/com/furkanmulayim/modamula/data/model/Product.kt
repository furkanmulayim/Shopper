package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val description: String? = "",
    var documentId: String? = "",
    val currentPrice: String? = "",
    val hastags: String? = "",
    var id: Int? = 0,
    val relatedProductId: String? = "",
    val image: String? = "",
    val discDesc: String? = "",
    var active: Int? = 0,
    var cargoPrice: Int? = 0,
    var producerSelect: Int? = 0,
    var new: Int? = 0,
    val name: String? = "",
    val category: String? = "",
    val number: String? = "",
    val beforePrice: String? = "",
    val color: String? = "",
    val unitSold: Int? = 0,
    val compatibleSize: String? = ""
) : Parcelable