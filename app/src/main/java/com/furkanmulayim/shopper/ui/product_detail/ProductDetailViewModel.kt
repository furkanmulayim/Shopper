package com.furkanmulayim.shopper.ui.product_detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.model.Product
import com.furkanmulayim.shopper.repository.ProductRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val cpr: ProductRepository
) : BaseViewModel(application) {

    var productItem: MutableLiveData<Product> = MutableLiveData()
    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    init {
        getSavedStateHandle()
        fetchData()
    }

    private fun fetchData() {
        _productList = cpr.getData()
    }

    private fun getSavedStateHandle() {
        viewModelScope.launch {
            savedStateHandle.get<Product>("ProductItem")?.let {
                productItem.value = it
            }
        }
    }

    val imagesList = arrayOf(
        R.drawable.dene,
        R.drawable.denek,
        R.drawable.item,
        R.drawable.denek,
        R.drawable.denek,
        R.drawable.denek
    )


    val colorVariantList = arrayListOf(
        "R.drawable.dene",
        "R.drawable.denek",
        "R.drawable.item"
    )

    val sizeList = arrayListOf(
        "35",
        "36",
        "37",
        "38"
    )

    val similarList = arrayListOf(
        "R.drawable.dene",
        "R.drawable.denek",
        "R.drawable.item"
    )
}