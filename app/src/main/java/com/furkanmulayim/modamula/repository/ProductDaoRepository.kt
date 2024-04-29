package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.database.product.ProductDao
import com.furkanmulayim.modamula.data.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDaoRepository(var productDao: ProductDao) {

    private var productList: MutableLiveData<List<Product>> = MutableLiveData()

    fun getAllProductListSqlite(): MutableLiveData<List<Product>> {
        getAll()
        return productList
    }


    private fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            productList.postValue(productDao.getAllProducts())
        }
    }

    fun setAllProductListSqlite(
        producList: List<Product>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.deleteAllProduct()
            val ll = productDao.instert(*producList.toTypedArray())
            var i = 0
            while (i < producList.size) {
                producList[i].uuid = ll[i].toInt()
                i++
            }
        }
    }
}