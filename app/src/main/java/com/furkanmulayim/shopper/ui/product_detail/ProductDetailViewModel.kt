package com.furkanmulayim.shopper.ui.product_detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.model.Fiyat
import com.furkanmulayim.shopper.data.model.Indirim
import com.furkanmulayim.shopper.data.model.Lojik
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.tarifce.base.BaseViewModel
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel(application) {

    var productItem: MutableLiveData<ProductItem> = MutableLiveData()

    init {
        getSavedStateHandle()
    }

    private fun getSavedStateHandle() {
        viewModelScope.launch {
            savedStateHandle.get<ProductItem>("ProductItem")?.let {
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