package com.furkanmulayim.modamula.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Slider
import com.google.firebase.firestore.CollectionReference

class SliderDataSource(private val collectionSlider: CollectionReference) {

    private var slideList = MutableLiveData<List<Slider>>()

    fun getData(): MutableLiveData<List<Slider>> {
        collectionSlider.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Slider>()
                for (i in value.documents) {
                    val slide = i.toObject(Slider::class.java)
                    if (slide != null) {
                        list.add(slide)
                    }
                }
                slideList.value = list
            }
        }
        return slideList
    }
}