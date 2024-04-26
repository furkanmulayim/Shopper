package com.furkanmulayim.modamula.di

import com.furkanmulayim.modamula.data.datasource.SliderDataSource
import com.furkanmulayim.modamula.repository.SliderRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModuleSlider {

    @Provides
    @Singleton
    fun providesCollectSliderRepos(): SliderDataSource {
        return SliderDataSource(providesSliderCollectionReference())
    }

    @Provides
    @Singleton
    fun providesSliderRepository(sds: SliderDataSource): SliderRepository {
        return SliderRepository(sds)
    }

    @Provides
    @Singleton
    fun providesSliderCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("slider")
    }
}
