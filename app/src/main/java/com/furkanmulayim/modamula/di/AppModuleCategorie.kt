package com.furkanmulayim.modamula.di

import com.furkanmulayim.modamula.data.datasource.CategorieDataSource
import com.furkanmulayim.modamula.repository.CategorieRepository
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
class AppModuleCategorie {

    @Provides
    @Singleton
    fun providesCollectCategorieRepos(): CategorieDataSource {
        return CategorieDataSource(providesCategorieCollectionReference())
    }

    @Provides
    @Singleton
    fun providesCategorieRepository(sds: CategorieDataSource): CategorieRepository {
        return CategorieRepository(sds)
    }

    @Provides
    @Singleton
    fun providesCategorieCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("category")
    }
}
