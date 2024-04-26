package com.furkanmulayim.modamula.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Billing
import com.google.firebase.firestore.CollectionReference

class BillingDataSource(private val collectionBilling: CollectionReference) {

    private var billist = MutableLiveData<List<Billing>>()

    fun getData(): MutableLiveData<List<Billing>> {
        collectionBilling.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Billing>()
                for (i in value.documents) {
                    val bill = i.toObject(Billing::class.java)
                    if (bill != null) {
                        list.add(bill)
                    }
                }
                billist.value = list
            }
        }
        return billist
    }
}