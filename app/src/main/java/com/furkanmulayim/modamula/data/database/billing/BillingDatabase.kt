package com.furkanmulayim.modamula.data.database.billing

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.modamula.data.model.Billing

@Database(entities = [Billing::class], version = 1)
abstract class BillingDatabase : RoomDatabase() {

    abstract fun billingDao(): BillingDao

    companion object {
        @Volatile
        private var instance: BillingDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, BillingDatabase::class.java, "billingdatabase"
        ).build()
    }
}