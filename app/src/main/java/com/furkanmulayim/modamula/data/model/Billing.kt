package com.furkanmulayim.modamula.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "billing")
data class Billing(
    @ColumnInfo("bankName") var bankName: String? = "",
    @ColumnInfo("descBill") var descBill: String? = "",
    @ColumnInfo("descCargo") var descCargo: String? = "",
    @ColumnInfo("descCargoCompany") var descCargoCompany: String? = "",
    @ColumnInfo("dolapAccount") var dolapAccount: String? = "",
    @ColumnInfo("iban") var iban: String? = "",
    @ColumnInfo("inTouch") var inTouch: String? = "",
    @ColumnInfo("instagramAccount") var instagramAccount: String? = "",
    @ColumnInfo("nameSurname") var nameSurname: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}