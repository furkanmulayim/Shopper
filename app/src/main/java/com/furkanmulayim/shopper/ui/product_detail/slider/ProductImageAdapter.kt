package com.furkanmulayim.shopper.ui.product_detail.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.databinding.ViewPagerItemImageBinding
import com.furkanmulayim.shopper.utils.onSingleClickListener

class ProductImageAdapter(
    private val context: Context,
    private val images: Array<Int>
) :
    RecyclerView.Adapter<ProductImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ViewPagerItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageResId: Int, position: Int, size: Int) {
            binding.imageView.setImageResource(imageResId)
            binding.imageView.onSingleClickListener {
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
