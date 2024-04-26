package com.furkanmulayim.modamula.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.modamula.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel
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
