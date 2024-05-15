package com.furkanmulayim.modamula.ui.home.modal_bottom_sheet

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.furkanmulayim.modamula.utils.stringToList
import com.furkanmulayim.tarifce.base.BaseViewModel

class ColorVariantViewModel(application: Application, var ssh: SavedStateHandle) :
    BaseViewModel(application) {

    private var arrayList: ArrayList<String> = arrayListOf()
    private val _stringList = MutableLiveData<ArrayList<String>>()
    val stringList: MutableLiveData<ArrayList<String>> get() = _stringList

    init {
        getBundle()
    }

    private fun getBundle() {
        val idText = ssh.get<String>("variant_ids")
        idText?.let { s ->
            arrayList.addAll(stringToList(s))
            arrayList.let {
                _stringList.value = it
            }
        }

    }
}