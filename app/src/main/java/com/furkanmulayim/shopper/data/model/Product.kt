package com.furkanmulayim.shopper.data.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Product(
    var id: Int? = 0,
    val isim: String? = "",
    val aciklama: String? = "",
    val image: String? = "",
    val gecerliFiyat: String? = "",
    val oncekiFiyat: String? = "",
    val indirimAciklama: String? = "",
    val kategori: String? = "",
    val numara: String? = "",
    val renk: String? = "",
    val renkSecenek: Int? = 0,
    val satilanAdet: Int? = 0,
    val ilgiliUrunId: String? = "",
    val hastags: String? = "",
    val isAktif: Boolean? = true,
    val isKargoUcret: Boolean? = true,
    val isReelsActive: Boolean? = true,
    val isSiparisAlim: Boolean? = true,
    val isUreticiSecimi: Boolean? = true,
    val isYeni: Boolean? = true
) : Serializable,

    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(isim)
        parcel.writeString(aciklama)
        parcel.writeString(image)
        parcel.writeString(gecerliFiyat)
        parcel.writeString(oncekiFiyat)
        parcel.writeString(indirimAciklama)
        parcel.writeString(kategori)
        parcel.writeString(numara)
        parcel.writeString(renk)
        parcel.writeValue(renkSecenek)
        parcel.writeValue(satilanAdet)
        parcel.writeString(ilgiliUrunId)
        parcel.writeString(hastags)
        parcel.writeValue(isAktif)
        parcel.writeValue(isKargoUcret)
        parcel.writeValue(isReelsActive)
        parcel.writeValue(isSiparisAlim)
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