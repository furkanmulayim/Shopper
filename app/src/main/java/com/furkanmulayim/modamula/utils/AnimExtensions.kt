package com.furkanmulayim.modamula.utils

import android.view.View

fun animSearch(view: View) {
    view.alpha = 0f
    view.postDelayed({
        view.animate()
            .alpha(1f)
            .setDuration(300)
    }, 0)
}

fun animSlider(view: View) {
    view.alpha = 0f
    view.postDelayed({
        view.animate()
            .alpha(1f)
            .setDuration(300)
    }, 100)
}

fun animProduct(view: View) {
    view.alpha = 0f
    view.postDelayed({
        view.animate()
            .alpha(1f)
            .setDuration(300)
    }, 200)
}

