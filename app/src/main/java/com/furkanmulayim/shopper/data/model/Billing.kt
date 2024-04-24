package com.furkanmulayim.shopper.data.model

import java.io.Serializable

data class Billing(
    val bankName: String? = "",
    val descBill: String? = "",
    val descCargo: String? = "",
    val descCargoCompany: String? = "",
    val dolapAccount: String? = "",
    val iban: String? = "",
    val intouch: String? = "",
    val instagramAccount: String? = "",
    val nameSurname: String? = ""
) : Serializable