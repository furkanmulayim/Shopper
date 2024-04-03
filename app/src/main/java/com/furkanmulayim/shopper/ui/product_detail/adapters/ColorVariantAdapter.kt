package com.furkanmulayim.shopper.ui.product_detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.databinding.ItemProductColorVariantBinding
import com.google.android.material.imageview.ShapeableImageView

class ColorVariantAdapter(
    private val dataList: ArrayList<String>,
) : RecyclerView.Adapter<ColorVariantAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductColorVariantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.image

        fun bind(url: String) {
            //image.loadImage(url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductColorVariantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<String>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
