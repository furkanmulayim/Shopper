package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.database.product.ProductDao
import com.furkanmulayim.modamula.data.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDaoRepository(private var productDao: ProductDao) {

    private var productList: MutableLiveData<List<Product>> = MutableLiveData()
    private var product: MutableLiveData<Product> = MutableLiveData()

    fun getLikeDatas(): MutableLiveData<List<Product>> {
        getAll()
        return productList
    }

    fun getSingleLikeData(id: Int): MutableLiveData<Product> {
        getSingle(id)
        return product
    }

    fun saveLikeData(product: Product) {
        saveProduct(product)
    }

    fun deleteSingleData(id: Int) {
        deleteSingle(id)
    }


    private fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            productList.postValue(productDao.getAllProducts())
        }
    }

    private fun getSingle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            product.postValue(productDao.getProduct(id))
        }
    }

    private fun saveProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            val uuid = productDao.insertFav(product)
            product.uuid = uuid[0].toInt()
        }
    }

    private fun deleteSingle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.deleteSingle(id)
        }
    }
}