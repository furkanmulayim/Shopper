package com.furkanmulayim.modamula.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Notifications
import com.google.firebase.firestore.CollectionReference

class NotificationsDataSource(private val collectionSlider: CollectionReference) {

    private var notify = MutableLiveData<List<Notifications>>()

    fun getData(): MutableLiveData<List<Notifications>> {
        collectionSlider.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Notifications>()
                for (i in value.documents) {
                    val slide = i.toObject(Notifications::class.java)
                    if (slide != null) {
                        list.add(slide)
                    }
                }
                notify.value = list
            }
        }
        return notify
    }
}