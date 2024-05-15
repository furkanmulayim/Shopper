package com.furkanmulayim.modamula.di

import com.furkanmulayim.modamula.data.datasource.NotificationsDataSource
import com.furkanmulayim.modamula.repository.NotificationsRepository
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
class AppModuleNotify {

    @Provides
    @Singleton
    fun providesCollectNotifyRepos(): NotificationsDataSource {
        return NotificationsDataSource(providesNotifyCollectionReference())
    }

    @Provides
    @Singleton
    fun providesNotifyRepository(nds: NotificationsDataSource): NotificationsRepository {
        return NotificationsRepository(nds)
    }

    @Provides
    @Singleton
    fun providesNotifyCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("notifications")
    }
}
