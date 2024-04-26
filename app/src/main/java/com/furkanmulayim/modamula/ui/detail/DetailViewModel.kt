package com.furkanmulayim.modamula.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.repository.ProductRepository
import com.furkanmulayim.modamula.utils.stringToList
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val cpr: ProductRepository
) : BaseViewModel(application) {

    val similarProductItem: MutableLiveData<List<Product>> = MutableLiveData()
    var productItem: MutableLiveData<Product> = MutableLiveData()

    var coloVariantList: MutableLiveData<List<String>> = MutableLiveData()
    var sizedList: MutableLiveData<List<String>> = MutableLiveData()
    var imageList: MutableLiveData<List<String>> = MutableLiveData()
    var similarList: MutableLiveData<List<String>> = MutableLiveData()
    var c: MutableLiveData<Product> = MutableLiveData()

    init {
        getSavedStateHandle()
    }

    private fun getSavedStateHandle() {
        viewModelScope.launch {
            savedStateHandle.get<Product>("ProductItem")?.let {
                productItem.value = it
                imageList.value = it.image?.let { images -> stringToList(images) }
                sizedList.value = it.uyumluBedenler?.let { size -> stringToList(size) }
                coloVariantList.value = it.image?.let { variant -> stringToList(variant) }
                similarList.value = it.ilgiliUrunId?.let { sim -> stringToList(sim) }
            }
        }
    }

    fun searchById(id: Int): Product? {
        val a = cpr.getData()
        return a.value?.firstOrNull { it.id == id }
    }

}