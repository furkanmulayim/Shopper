package com.furkanmulayim.shopper.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.shopper.data.datasource.BillingDataSource
import com.furkanmulayim.shopper.data.model.Billing

class BillingRepository(private var pds: BillingDataSource) {

    fun getData(): MutableLiveData<List<Billing>> = pds.getData()

}