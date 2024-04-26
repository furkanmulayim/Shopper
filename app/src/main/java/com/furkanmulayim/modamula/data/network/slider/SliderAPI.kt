package com.furkanmulayim.modamula.data.network.slider

import com.furkanmulayim.modamula.data.model.Slider
import io.reactivex.Single
import retrofit2.http.GET

interface SliderAPI {
    @GET("modapatik/patik/main/slider.json")
    fun getFood(): Single<List<Slider>>
}