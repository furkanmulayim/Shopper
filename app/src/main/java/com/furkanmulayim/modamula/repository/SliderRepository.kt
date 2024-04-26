package com.furkanmulayim.modamula.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Slider
import com.furkanmulayim.modamula.data.network.slider.SliderAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SliderRepository(private var frepo: SliderAPIService) {

    private var slider = MutableLiveData<List<Slider>>()
    private val disposable = CompositeDisposable()

    init {
        getSliderData()
    }

    fun getData(): MutableLiveData<List<Slider>> {
        return slider
    }

    private fun getSliderData() {
        disposable.add(
            frepo.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ products ->
                    slider.value = products
                }, { error ->
                    error.localizedMessage?.let { Log.d("LOGDF: getSliderData() ->", it) }
                })
        )
    }

}