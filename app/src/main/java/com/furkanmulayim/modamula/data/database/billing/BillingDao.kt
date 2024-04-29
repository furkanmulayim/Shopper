package com.furkanmulayim.modamula.data.database.billing

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.modamula.data.model.Billing

@Dao
interface BillingDao {

    @Insert
    fun instert(billing: Billing): Long

    @Query("SELECT * FROM billing")
    fun getAllBilling(): Billing

    @Query("DELETE FROM billing")
    fun deleteAllBilling()

}