package com.furkanmulayim.modamula.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.repository.ProductDaoRepository
import com.furkanmulayim.modamula.utils.stringToList
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val productDao: ProductDaoRepository
) : BaseViewModel(application) {

    var productItem: MutableLiveData<Product> = MutableLiveData()
    private var productSizeList: MutableLiveData<List<String>> = MutableLiveData()
    var favItem: MutableLiveData<Product> = MutableLiveData()
    var productImages: MutableLiveData<String> = MutableLiveData()

    init {
        getSavedStateHandle()
    }

    private fun getSavedStateHandle() {
        viewModelScope.launch {
            savedStateHandle.get<Product>("ProductItem")?.let {
                productItem.value = it
                it.id?.let { it1 -> isFavProduct(it1) }
                productSizeList.value = it.compatibleSize?.let { size -> stringToList(size) }
            }
        }
    }

    fun saveFavoriProduct(prod: Product) {
        productDao.saveLikeData(prod)
    }

    fun deleteSingleProducct(id: Int) {
        productDao.deleteSingleData(id)
    }

    private fun isFavProduct(id: Int) {
        favItem = productDao.getSingleLikeData(id)
    }
}