package com.furkanmulayim.shopper.di

import com.furkanmulayim.shopper.data.datasource.ProductDataSource
import com.furkanmulayim.shopper.repository.ProductRepository
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
class AppModuleProduct {

    @Provides
    @Singleton
    fun providesCollectProductRepos(): ProductDataSource {
        return ProductDataSource(providesProductsCollectionReference())
    }

    @Provides
    @Singleton
    fun providesProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return ProductRepository(productDataSource)
    }

    @Provides
    @Singleton
    fun providesProductsCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("product")
    }

}
