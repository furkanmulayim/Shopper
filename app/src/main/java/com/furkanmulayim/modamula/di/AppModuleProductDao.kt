package com.furkanmulayim.modamula.di

import android.content.Context
import androidx.room.Room
import com.furkanmulayim.modamula.data.database.product.ProductDao
import com.furkanmulayim.modamula.data.database.product.ProductDatabase
import com.furkanmulayim.modamula.repository.ProductDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleProductDao {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext, ProductDatabase::class.java, "productdatabase"
        ).build()
    }

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideProductDaoRepository(dao: ProductDao): ProductDaoRepository {
        return ProductDaoRepository(dao)
    }
}
