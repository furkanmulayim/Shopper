package com.furkanmulayim.modamula.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Billing
import com.google.firebase.firestore.CollectionReference

class BillingDataSource(private val collectionBilling: CollectionReference) {

    fun getData(): MutableLiveData<Billing?> {
        val billingLiveData = MutableLiveData<Billing?>()

        collectionBilling.document("1").get()
            .addOnSuccessListener { documentSnapshot ->
                // Belge başarıyla alındığında
                println("LOGDF: BAŞARI")
                val billing = documentSnapshot.toObject(Billing::class.java)
                if (billing != null) {
                    billingLiveData.value = billing
                }
            }
            .addOnFailureListener { exception ->
                exception.localizedMessage?.let { Log.e("LOGDF: Billing Data Source", it) }
            }

        return billingLiveData
    }

}