package com.furkanmulayim.modamula.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.data.model.Slider
import com.furkanmulayim.modamula.repository.ProductDaoRepository
import com.furkanmulayim.modamula.repository.ProductRepository
import com.furkanmulayim.modamula.repository.SliderDaoRepository
import com.furkanmulayim.modamula.repository.SliderRepository
import com.furkanmulayim.modamula.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val sliderRepository: SliderRepository,
    private val sliderDaoRepo: SliderDaoRepository,
    private val productFirebaseRepo: ProductRepository,
    private val productDaoRepo: ProductDaoRepository
) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())
    var isSqliteData: Boolean = false

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList


    //Slider
    private var _sliderList = MutableLiveData<List<Slider>>()
    val sliderList: LiveData<List<Slider>>
        get() = _sliderList

    init {
        isAppAlreadyBefore()
        getDatas()
    }

    private fun isAppAlreadyBefore() {
        sharedPrefs.saveWelcome()
    }

    private fun getDatas() {
        val updateTime = sharedPrefs.getTime()
        if (updateTime != null && updateTime != 0L) {
            println("LOGDF: SQLİTEEEEEEE")
            getSqlite()
        } else {
            println("LOGDF: APIIIIIIIIII")
            fetchData()
        }
    }


    private fun fetchData() {
        _productList = productFirebaseRepo.getData()
        _sliderList = sliderRepository.getData()
        isSqliteData = false
    }

    fun setSqliteproductList(productList: List<Product>) {
        sharedPrefs.saveTime(System.nanoTime())
        productDaoRepo.setAllProductListSqlite(productList)
    }

    fun setSqliteSliderList(sliderList: List<Slider>) {
        sharedPrefs.saveTime(System.nanoTime())
        sliderDaoRepo.setAllSliderListSqlite(sliderList)
    }

    private fun getSqlite() {
        _productList = productDaoRepo.getAllProductListSqlite()
        _sliderList = sliderDaoRepo.getAllSliderListSqlite()
        isSqliteData = true
    }
}