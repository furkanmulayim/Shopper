package com.furkanmulayim.modamula.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.databinding.ItemProductSizesBinding

class CompatibleSizeAdapter(
    private val dataList: List<String>,
) : RecyclerView.Adapter<CompatibleSizeAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductSizesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val sizeText: TextView = binding.sizeText

        fun bind(size: String) {
            sizeText.text = size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductSizesBinding.inflate(
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
}
