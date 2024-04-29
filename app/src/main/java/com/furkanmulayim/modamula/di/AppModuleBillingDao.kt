package com.furkanmulayim.modamula.di

import android.content.Context
import androidx.room.Room
import com.furkanmulayim.modamula.data.database.billing.BillingDao
import com.furkanmulayim.modamula.data.database.billing.BillingDatabase
import com.furkanmulayim.modamula.repository.BillingDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleBillingDao {

    @Provides
    @Singleton
    fun provideBillingDatabase(@ApplicationContext context: Context): BillingDatabase {
        return Room.databaseBuilder(
            context.applicationContext, BillingDatabase::class.java, "billingdatabase"
        ).build()
    }

    @Provides
    fun provideBillingDao(database: BillingDatabase): BillingDao {
        return database.billingDao()
    }

    @Provides
    fun provideBillingDaoRepository(dao: BillingDao): BillingDaoRepository {
        return BillingDaoRepository(dao)
    }
}
