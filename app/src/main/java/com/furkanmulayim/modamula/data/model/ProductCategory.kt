package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductCategory(
    val back: Int,
    val image: Int,
    val name: String
) : Parcelable