package com.furkanmulayim.modamula.ui.notifications

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Notifications
import com.furkanmulayim.modamula.repository.NotificationsRepository
import com.furkanmulayim.tarifce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    application: Application,
    private val notifyRepo: NotificationsRepository
) : BaseViewModel(application) {

    var notify: MutableLiveData<List<Notifications>> = MutableLiveData()
    fun getNotifications() {
        getData()
    }

    private fun getData() {
        notify = notifyRepo.getData()
    }
}