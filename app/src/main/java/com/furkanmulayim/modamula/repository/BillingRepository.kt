package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.datasource.BillingDataSource
import com.furkanmulayim.modamula.data.model.Billing

class BillingRepository(private var pds: BillingDataSource) {

    fun getData(): MutableLiveData<List<Billing>> = pds.getData()

}