package com.furkanmulayim.modamula.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.data.model.Slider
import com.furkanmulayim.modamula.repository.ProductRepository
import com.furkanmulayim.modamula.repository.SliderRepository
import com.furkanmulayim.modamula.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val sliderRepository: SliderRepository,
    private val productFirebaseRepo: ProductRepository
) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    //Slider
    private var _sliderList = MutableLiveData<List<Slider>>()
    val sliderList: LiveData<List<Slider>>
        get() = _sliderList

    init {
        isAppAlreadyBefore()
    }

    private fun isAppAlreadyBefore() {
        sharedPrefs.saveWelcome()
    }

    fun getDatas() {
        fetchData()
    }

    private fun fetchData() {
        _productList = productFirebaseRepo.getData()
        _sliderList = sliderRepository.getData()
    }
}