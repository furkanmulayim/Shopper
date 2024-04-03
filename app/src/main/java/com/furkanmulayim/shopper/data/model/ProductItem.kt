package com.furkanmulayim.shopper.data.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val id: Int,
    val image: String,
    val isim: String,
    val kategori: String,
    val aciklama: String,
    val numara: String,
    val renk: Int,
    val renkSecenek: List<String>,
    val satilanAdet: Int,
    val fiyat: Fiyat,
    val indirim: Indirim,
    val lojik: Lojik,
    val ilgiliUrunler: List<String>,
    val hastag: String
) : Parcelable


@Parcelize
data class Fiyat(
    @SerializedName("oncekiFiyat") val oncekiFiyat: String,
    @SerializedName("gecerliFiyat") val gecerliFiyat: String
) : Parcelable

@Parcelize
data class Indirim(
    @SerializedName("indirimAciklama") val indirimAciklama: String,
    @SerializedName("indirimYuzde") val indirimYuzde: String
) : Parcelable

@Parcelize
data class Lojik(
    @SerializedName("isAktif") val isAktif: Boolean,
    @SerializedName("isYeni") val isYeni: Boolean,
    @SerializedName("isKargoUcret") val isKargoUcret: Boolean,
    @SerializedName("isSiparisAlim") val isSiparisAlim: Boolean,
    @SerializedName("isUreticiSecimi") val isUreticiSecimi: Boolean,
    @SerializedName("isReelsActive") val isReelsActive: Boolean
) : Parcelable