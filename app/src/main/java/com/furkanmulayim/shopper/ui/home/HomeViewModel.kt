package com.furkanmulayim.shopper.ui.home

import android.app.Application
import com.furkanmulayim.shopper.utils.SharedPrefs
import com.furkanmulayim.tarifce.base.BaseViewModel

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var sharedPrefs = SharedPrefs(getApplication())

    init {
        isAppAlreadyBefore()
    }

    private fun isAppAlreadyBefore() {
        //kullanıcı uygulamayı daha önceden açtıysa kaydeder
        sharedPrefs.saveWelcome()
    }
}