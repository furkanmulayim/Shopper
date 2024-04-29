package com.furkanmulayim.modamula.data.database.slider

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.modamula.data.model.Slider

@Dao
interface SliderDao {

    @Insert
    fun instert(vararg slider: Slider): List<Long>

    @Query("SELECT * FROM slider")
    fun getAllSlider(): List<Slider>

    @Query("DELETE FROM slider")
    fun deleteAllSlider()

}