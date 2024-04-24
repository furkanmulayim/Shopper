package com.furkanmulayim.shopper.data.network.slider

import com.furkanmulayim.shopper.data.model.Slider
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SliderAPIService {

    private val baseUr = "https://raw.githubusercontent.com/"
    private val api =
        Retrofit.Builder().baseUrl(baseUr).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(SliderAPI::class.java)

    fun getData(): Single<List<Slider>> {
        return api.getFood()
    }

}