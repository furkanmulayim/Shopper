package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.database.billing.BillingDao
import com.furkanmulayim.modamula.data.model.Billing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BillingDaoRepository(private var bdao: BillingDao) {

    private var billing: MutableLiveData<Billing?> = MutableLiveData()

    fun getAllBillingListSqlite(): MutableLiveData<Billing?> {
        getAll()
        return billing
    }


    private fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            billing.postValue(bdao.getAllBilling())
        }
    }


    fun setAllBillingListSqlite(
        bill: Billing
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            bdao.instert(bill)
        }
    }
}