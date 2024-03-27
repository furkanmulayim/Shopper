package com.furkanmulayim.shopper.ui.home.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.shopper.R
import kotlinx.coroutines.*

class ImageAdapter(private val context: Context, private val viewPager: ViewPager2) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val images = arrayOf(
        R.drawable.denek,
        R.drawable.denek,
        R.drawable.denek,
        R.drawable.denek,
        R.drawable.denek,
        R.drawable.denek
    )

    private var autoScrollJob: Job? = null
    private var currentItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
        startAutoScroll()
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageResId: Int) {
            imageView.setImageResource(imageResId)
        }
    }

    private fun startAutoScroll() {
        autoScrollJob?.cancel()
        autoScrollJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(3000) // 3 saniye aralıkla geçiş yap
                viewPager.currentItem = (currentItem + 1) % itemCount
                currentItem = (currentItem + 1) % itemCount
            }
        }
    }

    fun stopAutoScroll() {
        autoScrollJob?.cancel()
    }
}
