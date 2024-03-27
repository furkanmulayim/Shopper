package com.furkanmulayim.shopper.data.model

import androidx.annotation.StringRes

data class ProductItem(
    var yeniMi:Boolean,
    var indirimAciklama:String,
    var renkleri:Int,
    var adi:String,
    var eskiFiyat:String,
    var yeniFiyat:String,
    var indirimOrani:String,
    var isKargo:Boolean
)