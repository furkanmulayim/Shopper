package com.furkanmulayim.modamula.ui.detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.databinding.ItemProductSimilarBinding

class SimilarProductAdapter(
    private val dataList: ArrayList<String>
) : RecyclerView.Adapter<SimilarProductAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductSimilarBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.image

        fun bind(item: String) {
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
    fun updateList(newList: ArrayList<String>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
