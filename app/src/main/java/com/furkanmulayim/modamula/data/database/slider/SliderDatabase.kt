package com.furkanmulayim.modamula.data.database.slider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.modamula.data.model.Slider

@Database(entities = [Slider::class], version = 1)
abstract class SliderDatabase : RoomDatabase() {

    abstract fun slidergDao(): SliderDao

    companion object {
        @Volatile
        private var instance: SliderDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, SliderDatabase::class.java, "sliderdatabase"
        ).build()
    }
}