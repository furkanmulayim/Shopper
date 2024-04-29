package com.furkanmulayim.modamula.data.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.modamula.data.model.Product

@Dao
interface ProductDao {

    @Insert
    fun instert(vararg product: Product): List<Long>

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE uuid = :id")
    fun getProduct(id: Int): Product

    @Query("DELETE FROM product")
    fun deleteAllProduct()

}