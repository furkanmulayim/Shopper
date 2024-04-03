package com.furkanmulayim.shopper.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("isim") val isim: String,
    @SerializedName("kategori") val kategori: String,
    @SerializedName("aciklama") val aciklama: String,
    @SerializedName("numara") val numara: String,
    @SerializedName("renk") val renk: Int,
    @SerializedName("renkSecenek") val renkSecenek: String,
    @SerializedName("satilanAdet") val satilanAdet: Int,
    @SerializedName("fiyat") val fiyat: Fiyat,
    @SerializedName("indirimAciklama") val indirimAciklama: String,
    @SerializedName("lojik") val lojik: Lojik,
    @SerializedName("ilgiliUrunler") val ilgiliUrunler: String,
    @SerializedName("hastag") val hastag: String
) : Parcelable


@Parcelize
data class Fiyat(
    @SerializedName("oncekiFiyat") val oncekiFiyat: String,
    @SerializedName("gecerliFiyat") val gecerliFiyat: String
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