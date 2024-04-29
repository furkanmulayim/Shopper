package com.furkanmulayim.modamula.ui.shop

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Billing
import com.furkanmulayim.modamula.repository.BillingDaoRepository
import com.furkanmulayim.modamula.repository.BillingRepository
import com.furkanmulayim.modamula.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    application: Application,
    private val billingFirebaseRepo: BillingRepository,
    private val billingDaoRepo: BillingDaoRepository
) : BaseViewModel(application) {

    private val sharedPrefs = SharedPrefs(application)

    var isSqliteData: Boolean = false

    //Bill List
    private var _billing = MutableLiveData<Billing?>()
    val billing: LiveData<Billing?>
        get() = _billing

    init {
        getDatas()
    }

    private fun getBillData() {
        _billing = billingFirebaseRepo.getData()
        isSqliteData = false
    }

    fun setSqliteData(bill: Billing) {
        sharedPrefs.saveTimeForBillingDownload(System.nanoTime())
        billingDaoRepo.setAllBillingListSqlite(bill)
    }

    private fun getSqlite() {
        _billing = billingDaoRepo.getAllBillingListSqlite()
        isSqliteData = true
    }

    private fun getDatas() {
        val updateTime = sharedPrefs.getSaveTimeBilling()
        if (updateTime != null && updateTime != 0L) {
            println("LOGDF: SQLİTEEEEEEE")
            getSqlite()
        } else {
            println("LOGDF: APIIIIIIIIII")
            getBillData()
        }
        getBillData()
    }
}