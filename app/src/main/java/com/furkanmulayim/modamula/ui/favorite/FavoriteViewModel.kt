package com.furkanmulayim.modamula.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.repository.ProductDaoRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    app: Application,
    private val productRepo: ProductDaoRepository
) :
    BaseViewModel(app) {

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    fun getDatas() {
        _productList = productRepo.getLikeDatas()
    }

    fun deleteFav(id: Int) {
        productRepo.deleteSingleData(id)
    }
}