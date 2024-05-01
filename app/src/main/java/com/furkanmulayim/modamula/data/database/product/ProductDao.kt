package com.furkanmulayim.modamula.data.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.modamula.data.model.Product

@Dao
interface ProductDao {

    @Insert
    fun instert(vararg product: Product): List<Long>

    @Insert
    fun insertFav(vararg product: Product): List<Long>

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProduct(id: Int): Product

    @Query("UPDATE product SET favorite = 1 WHERE id = :id")
    fun updateProductFavorite(id: Int)


    @Query("DELETE FROM product")
    fun deleteAllProduct()

    @Query("DELETE FROM product WHERE id = :id")
    fun deleteSingle(id: Int)

}