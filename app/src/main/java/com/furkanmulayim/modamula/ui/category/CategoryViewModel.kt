package com.furkanmulayim.modamula.ui.category

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.enums.CategoryName
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.data.model.ProductCategory
import com.furkanmulayim.modamula.repository.ProductRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    app: Application,
    private val ssh: SavedStateHandle,
    private val cpr: ProductRepository
) : BaseViewModel(app) {

    var isSearchFocused: MutableLiveData<Boolean> = MutableLiveData()
    private var _productList = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _productList

    init {
        getBundle()
        fetchData()
    }

    private fun getBundle() {
        viewModelScope.launch {
            val bundle = ssh.get<Boolean>("search")
            bundle?.let {
                isSearchFocused.value = it
            }
        }
    }

    private fun fetchData() {
        _productList = cpr.getData()
    }

    val categories = arrayListOf(
        ProductCategory(
            image = R.drawable.menu_item_shopping_car,
            back = R.drawable.category_item_white,
            name = CategoryName.TUMU.id
        ),
        ProductCategory(
            image = R.drawable.menu_item_shopping_car,
            back = R.drawable.category_item_white,
            name = CategoryName.JIK.id
        ),
        ProductCategory(
            image = R.drawable.menu_item_shopping_car,
            back = R.drawable.category_item_white,
            name = CategoryName.BEBEK.id
        )
    )


}