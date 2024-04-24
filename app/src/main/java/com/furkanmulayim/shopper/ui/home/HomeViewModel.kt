package com.furkanmulayim.shopper.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.shopper.data.model.Product
import com.furkanmulayim.shopper.data.model.Slider
import com.furkanmulayim.shopper.repository.ProductRepository
import com.furkanmulayim.shopper.repository.SliderRepository
import com.furkanmulayim.shopper.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val sliderRepository: SliderRepository,
    private val cpr: ProductRepository
) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())
    var denek = MutableLiveData<List<Product>>()


    //Product
    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList


    //Slider
    private var _sliderList = MutableLiveData<List<Slider>>()
    val sliderList: LiveData<List<Slider>>
        get() = _sliderList

    init {
        isAppAlreadyBefore()
        fetchData()
    }

    private fun isAppAlreadyBefore() {
        sharedPrefs.saveWelcome()
    }

    private fun fetchData() {
        _productList = cpr.getData()
        _sliderList = sliderRepository.getData()
    }
}