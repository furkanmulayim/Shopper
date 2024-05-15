package com.furkanmulayim.modamula.ui.shop

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Billing
import com.furkanmulayim.modamula.repository.BillingRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    application: Application,
    private val billingFirebaseRepo: BillingRepository
) : BaseViewModel(application) {

    //Bill List
    private var _billing = MutableLiveData<Billing?>()
    val billing: LiveData<Billing?>
        get() = _billing

    fun getBillData() {
        _billing = billingFirebaseRepo.getData()
    }


}