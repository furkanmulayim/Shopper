package com.furkanmulayim.shopper.ui.shop

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.shopper.data.model.Billing
import com.furkanmulayim.shopper.repository.BillingRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    application: Application,
    private val billingRepository: BillingRepository
) : BaseViewModel(application) {


    //Bill List
    private var _billing = MutableLiveData<List<Billing>>()
    val billing: LiveData<List<Billing>>
        get() = _billing

    init {
        getBillData()
    }

    private fun getBillData() {
        _billing = billingRepository.getData()
    }

}