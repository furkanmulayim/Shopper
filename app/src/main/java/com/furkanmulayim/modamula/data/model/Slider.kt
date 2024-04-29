package com.furkanmulayim.modamula.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "slider")
@Parcelize
data class Slider(
    @ColumnInfo("guide") var guide: String? = null,
    @ColumnInfo("image") var image: String? = null,
    @ColumnInfo("name") var name: String? = null
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}