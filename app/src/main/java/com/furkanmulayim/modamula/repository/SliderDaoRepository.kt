package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.database.slider.SliderDao
import com.furkanmulayim.modamula.data.model.Slider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SliderDaoRepository(private var sliderDao: SliderDao) {

    private var blist: MutableLiveData<List<Slider>> = MutableLiveData()

    fun getAllSliderListSqlite(): MutableLiveData<List<Slider>> {
        getAll()
        return blist
    }


    private fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            blist.postValue(sliderDao.getAllSlider())
        }
    }

    fun setAllSliderListSqlite(
        slist: List<Slider>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            sliderDao.deleteAllSlider()
            val ll = sliderDao.instert(*slist.toTypedArray())
            var i = 0
            println("LOGDF: repo-slider " + slist.size)
            while (i < slist.size) {
                slist[i].uuid = ll[i].toInt()
                i++
            }
        }
    }
}