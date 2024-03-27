package com.furkanmulayim.shopper.ui.home.slider

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ZoomOutPageTransformer(private val marginPx: Int) : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val density = page.resources.displayMetrics.density
        val marginDP = marginPx * density

        when {
            position < -1 -> { // Solunda olan sayfa
                page.translationX = -marginDP
                page.scaleX = 0.55f
                page.scaleY = 0.55f
            }
            position <= 1 -> { // Görünen sayfa
                val scaleFactor = 0.85f.coerceAtLeast(1 - abs(position))
                page.translationX = -marginDP * position
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            }
            else -> { // Sağında olan sayfa
                page.translationX = marginDP
                page.scaleX = 0.55f
                page.scaleY = 0.55f
            }
        }
    }
}
