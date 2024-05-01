package com.furkanmulayim.modamula.ui.category

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.modamula.data.model.Categorie
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.repository.CategorieRepository
import com.furkanmulayim.modamula.repository.ProductRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    app: Application,
    private val savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
    private val categorieRepository: CategorieRepository
) : BaseViewModel(app) {

    //HOVER SEARCH & KEYBOARD
    var isSearchFocused: MutableLiveData<Boolean> = MutableLiveData()

    //PRODUCTS
    private var _productList = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _productList

    //CATEGORİES
    private var _categorieList = MutableLiveData<List<Categorie>>()
    val categorie: LiveData<List<Categorie>>
        get() = _categorieList


    //DENEME
    private var _deneme = MutableLiveData<List<Product>>()
    val deneme: LiveData<List<Product>>
        get() = _deneme


    init {
        getBundle()
        fetchData()
    }

    private fun getBundle() {
        viewModelScope.launch {
            val bundle = savedStateHandle.get<Boolean>("search")
            bundle?.let {
                isSearchFocused.value = it
            }
        }
    }

    private fun fetchData() {
        _productList = productRepository.getData()
        _categorieList = categorieRepository.getData()
    }

    fun selectedCategQuery(categ: String) {
        _productList = productRepository.searchByCateg(categ)
    }

}