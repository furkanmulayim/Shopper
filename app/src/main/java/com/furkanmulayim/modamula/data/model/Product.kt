package com.furkanmulayim.modamula.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Product(
    var id: Int? = 0,
    var documentId: String? = "",
    val isim: String? = "",
    val aciklama: String? = "",
    val image: String? = "",
    val gecerliFiyat: String? = "",
    val oncekiFiyat: String? = "",
    val indirimAciklama: String? = "",
    val kategori: String? = "",
    val numara: String? = "",
    val renk: String? = "",
    val uyumluBedenler: String? = "",
    val satilanAdet: Int? = 0,
    val ilgiliUrunId: String? = "",
    val hastags: String? = "",
    var isAktif: Int? = 0,
    var isKargoUcret: Int? = 0,
    var isUreticiSecimi: Int? = 0,
    var isYeni: Int? = 0
) : Serializable,

    Parcelable {

    // getter ve setter metodları
    fun getIsAktif(): Int? {
        return isAktif
    }

    fun setIsAktif(isAktif: Int?) {
        this.isAktif = isAktif
    }

    fun getIsKargoUcret(): Int? {
        return isKargoUcret
    }

    fun setIsKargoUcret(isKargoUcret: Int?) {
        this.isKargoUcret = isKargoUcret
    }

    fun getIsUreticiSecimi(): Int? {
        return isUreticiSecimi
    }

    fun setIsUreticiSecimi(isUreticiSecimi: Int?) {
        this.isUreticiSecimi = isUreticiSecimi
    }

    fun getIsYeni(): Int? {
        return isYeni
    }

    fun setIsYeni(isYeni: Int?) {
        this.isYeni = isYeni
    }
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(), parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(), parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(documentId)
        parcel.writeString(isim)
        parcel.writeString(aciklama)
        parcel.writeString(image)
        parcel.writeString(gecerliFiyat)
        parcel.writeString(oncekiFiyat)
        parcel.writeString(indirimAciklama)
        parcel.writeString(kategori)
        parcel.writeString(numara)
        parcel.writeString(renk)
        parcel.writeString(uyumluBedenler)
        parcel.writeValue(satilanAdet)
        parcel.writeString(ilgiliUrunId)
        parcel.writeString(hastags)
        parcel.writeValue(isAktif)
        parcel.writeValue(isKargoUcret)
        parcel.writeValue(isUreticiSecimi)
        parcel.writeValue(isYeni)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}