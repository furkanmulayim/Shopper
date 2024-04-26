package com.furkanmulayim.modamula.di

import com.furkanmulayim.modamula.data.network.slider.SliderAPIService
import com.furkanmulayim.modamula.repository.SliderRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuleSlider {

    @Singleton
    @Component(modules = [NetworkModule::class])
    interface AppComponent {
        fun sliderRepository(): SliderRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {
        @Provides
        @Singleton
        fun provideSliderAPIService(): SliderAPIService {
            return SliderAPIService()
        }

        @Provides
        @Singleton
        fun provideProductRepository(apiService: SliderAPIService): SliderRepository {
            return SliderRepository(apiService)
        }
    }

}