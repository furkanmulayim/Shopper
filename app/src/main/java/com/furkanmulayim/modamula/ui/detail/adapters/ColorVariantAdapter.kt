package com.furkanmulayim.modamula.ui.detail.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.databinding.ItemProductColorsBinding
import com.google.android.material.imageview.ShapeableImageView

class ColorVariantAdapter(
    private val dataList: List<String>,
) : RecyclerView.Adapter<ColorVariantAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductColorsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.color

        fun bind(color: String) {
            val reColor = color.replace(" ", "")
            val selectedColor = Color.parseColor(reColor)
            image.setBackgroundColor(selectedColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductColorsBinding.inflate(
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
