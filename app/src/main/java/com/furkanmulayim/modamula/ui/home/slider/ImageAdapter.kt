package com.furkanmulayim.modamula.ui.home.slider

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.model.Slider
import com.furkanmulayim.modamula.utils.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageAdapter(
    private val context: Context,
    private val viewPager: ViewPager2,
    private val images: List<Slider>
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

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

        fun bind(item: Slider) {
            imageView.loadImage(item.image, R.drawable.png_failed)
            Log.d("logdf - hata ", item.image)
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
}
