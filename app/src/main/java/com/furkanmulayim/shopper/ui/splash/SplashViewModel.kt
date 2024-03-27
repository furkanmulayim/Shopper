package com.furkanmulayim.shopper.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.shopper.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())
    var isAppOpened: MutableLiveData<Boolean> = MutableLiveData()

    init {
        timeControl()
    }

    private fun timeControl(){
        viewModelScope.launch {
            delay(1800)
            isAppOpened.value = sharedPrefs.isAppOpened()
        }
    }


}
