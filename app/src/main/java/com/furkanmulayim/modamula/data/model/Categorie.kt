package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categorie(
    @ColumnInfo("active") var active: Int? = 0,
    @ColumnInfo("logo") var logo: String? = "",
    @ColumnInfo("name") var name: String? = "",
) : Parcelable