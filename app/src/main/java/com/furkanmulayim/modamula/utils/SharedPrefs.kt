package com.furkanmulayim.modamula.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedPrefs {

    companion object {
        const val PREFTIME = "preferences_time"

        const val billingSaveTime = "pref_billing_firebase"
        const val productSaveTime = "pref_product_firebase"
        const val sliderSaveTime = "pref_slider_firebase"

        const val preferencesWelcome = "preferences_welcome"
        const val preferencesLanguage = "preferences_language"

        private var sp: SharedPreferences? = null

        @Volatile
        private var instance: SharedPrefs? = null
        private val lock = Any()

        operator fun invoke(context: Context): SharedPrefs = instance ?: synchronized(lock) {

            instance ?: makeSharedPrefs(context).also {
                instance = it
            }
        }

        private fun makeSharedPrefs(context: Context): SharedPrefs {
            sp = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefs()
        }
    }

    fun saveLanguage(language: String) {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putString(preferencesLanguage, language)
            }
        }
    }

    fun getLanguage() = sp?.getString(preferencesLanguage, "")

    fun saveWelcome() {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putBoolean(preferencesWelcome, true)
            }
        }
    }

    fun isAppOpened() = sp?.getBoolean(preferencesWelcome, false)

    fun saveTime(time: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putLong(PREFTIME, time)
            }
        }
    }

    fun getSaveTimeBilling() = sp?.getLong(billingSaveTime, 0)
    fun saveTimeForBillingDownload(time: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putLong(billingSaveTime, time)
            }
        }
    }

    fun getSaveTimeProduct() = sp?.getLong(productSaveTime, 0)
    fun saveTimeForProductDownload(time: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putLong(productSaveTime, time)
            }
        }
    }

    fun getSaveTimeSlider() = sp?.getLong(sliderSaveTime, 0)
    fun saveTimeForSliderDownload(time: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putLong(sliderSaveTime, time)
            }
        }
    }


    fun getTime() = sp?.getLong(PREFTIME, 0)
}