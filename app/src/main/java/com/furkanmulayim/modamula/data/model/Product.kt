package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product")
data class Product(
    @ColumnInfo("description") var description: String? = "",
    @ColumnInfo("document") var documentId: String? = "",
    @ColumnInfo("currentPrice") var currentPrice: String? = "",
    @ColumnInfo("hastags") var hastags: String? = "",
    @ColumnInfo("relatedProductId") var relatedProductId: String? = "",
    @ColumnInfo("image") var image: String? = "",
    @ColumnInfo("discDesc") var discDesc: String? = "",
    @ColumnInfo("name") var name: String? = "",
    @ColumnInfo("category") var category: String? = "",
    @ColumnInfo("number") var number: String? = "",
    @ColumnInfo("beforePrice") var beforePrice: String? = "",
    @ColumnInfo("color") var color: String? = "",
    @ColumnInfo("compatibleSize") var compatibleSize: String? = "",
    @ColumnInfo("id") var id: Int? = 0,
    @ColumnInfo("unitSold") var unitSold: Int? = 0,
    @ColumnInfo("active") var active: Int? = 0,
    @ColumnInfo("cargoPrice") var cargoPrice: Int? = 0,
    @ColumnInfo("producerSelect") var producerSelect: Int? = 0,
    @ColumnInfo("new") var new: Int? = 0,
    @ColumnInfo("favorite") var favorite: Int? = 0
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}