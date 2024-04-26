package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Slider(
    val guide: String? = null,
    val image: String? = null,
    val name: String? = null
) : Parcelable