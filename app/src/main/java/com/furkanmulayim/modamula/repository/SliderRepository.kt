package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.datasource.SliderDataSource
import com.furkanmulayim.modamula.data.model.Slider

class SliderRepository(private var sliderDataSource: SliderDataSource) {
    fun getData(): MutableLiveData<List<Slider>> = sliderDataSource.getData()
}