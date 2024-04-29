package com.furkanmulayim.modamula.di

import android.content.Context
import androidx.room.Room
import com.furkanmulayim.modamula.data.database.slider.SliderDao
import com.furkanmulayim.modamula.data.database.slider.SliderDatabase
import com.furkanmulayim.modamula.repository.SliderDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleSliderDao {

    @Provides
    @Singleton
    fun provideSliderDatabase(@ApplicationContext context: Context): SliderDatabase {
        return Room.databaseBuilder(
            context.applicationContext, SliderDatabase::class.java, "sliderdatabase"
        ).build()
    }

    @Provides
    fun provideSliderDao(database: SliderDatabase): SliderDao {
        return database.slidergDao()
    }

    @Provides
    fun provideSliderDaoRepository(dao: SliderDao): SliderDaoRepository {
        return SliderDaoRepository(dao)
    }
}
