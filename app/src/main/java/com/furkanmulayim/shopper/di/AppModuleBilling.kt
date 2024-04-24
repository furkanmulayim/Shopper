package com.furkanmulayim.shopper.di

import com.furkanmulayim.shopper.data.datasource.BillingDataSource
import com.furkanmulayim.shopper.repository.BillingRepository
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
class AppModuleBilling {

    @Provides
    @Singleton
    fun providesCollectBillingRepos(): BillingDataSource {
        return BillingDataSource(providesBillingCollectionReference())
    }

    @Provides
    @Singleton
    fun providesBillingRepository(ds: BillingDataSource): BillingRepository {
        return BillingRepository(ds)
    }

    @Provides
    @Singleton
    fun providesBillingCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("billing")
    }
}
