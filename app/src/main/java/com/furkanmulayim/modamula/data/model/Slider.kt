package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Slider(
    val image: String,
    val name: String,
    val guide: String
) : Parcelable