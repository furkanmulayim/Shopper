package com.furkanmulayim.modamula.ui.detail.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.databinding.ViewPagerItemImageBinding
import com.furkanmulayim.modamula.utils.loadImage
import com.furkanmulayim.modamula.utils.onSingleClickListener

class ProductImageAdapter(
    private val context: Context,
    private val images: List<String>
) :
    RecyclerView.Adapter<ProductImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ViewPagerItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageLink: String, position: Int, size: Int) {
            binding.imageViewViewPagers.loadImage(imageLink, R.drawable.png_failed)
            binding.imageViewViewPagers.onSingleClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewPagerItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position], position, images.size)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
