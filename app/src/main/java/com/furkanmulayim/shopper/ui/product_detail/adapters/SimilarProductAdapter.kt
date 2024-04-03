package com.furkanmulayim.shopper.ui.product_detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.databinding.ItemProductSimilarBinding
import com.google.android.material.imageview.ShapeableImageView

class SimilarProductAdapter(
    private val dataList: ArrayList<String>
) : RecyclerView.Adapter<SimilarProductAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductSimilarBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.image

        fun bind(url: String) {
            //image.loadImage(url)
            println(url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = dataList[position]
        holder.bind(url)

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
