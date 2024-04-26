package com.furkanmulayim.modamula.ui.detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.ItemProductSimilarBinding
import com.furkanmulayim.modamula.utils.loadImage
import com.google.android.material.imageview.ShapeableImageView

class SimilarProductAdapter(
    private val dataList: ArrayList<Product>
) : RecyclerView.Adapter<SimilarProductAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductSimilarBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.image

        fun bind(item: Product) {
            val firstImage = item.image?.split(",")?.get(0)
            if (firstImage != null) {
                image.loadImage(firstImage, R.drawable.png_failed)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pro = dataList[position]
        holder.bind(pro)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Product>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
