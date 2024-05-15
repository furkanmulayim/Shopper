package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.datasource.NotificationsDataSource
import com.furkanmulayim.modamula.data.model.Notifications

class NotificationsRepository(private var notifyDataSource: NotificationsDataSource) {
    fun getData(): MutableLiveData<List<Notifications>> = notifyDataSource.getData()
}