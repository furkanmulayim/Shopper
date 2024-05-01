package com.furkanmulayim.modamula.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.model.Categorie
import com.furkanmulayim.modamula.databinding.ItemProductCategoryBinding
import com.furkanmulayim.modamula.utils.loadImage

class CategoryAdapter(
    private val dataList: ArrayList<Categorie>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.SCROLLBAR_POSITION_DEFAULT

    inner class ViewHolder(private val binding: ItemProductCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    selectedPosition = position
                    notifyDataSetChanged()
                    dataList[position].name?.let { it1 -> onClick(it1) }
                }
            }
        }

        fun bind(item: Categorie) {
            binding.apply {
                item.logo?.let { logo.loadImage(it, R.drawable.png_failed) }
                name.text = item.name
                if (adapterPosition == selectedPosition) {
                    itemFoodCategoryBack.setBackgroundResource(R.drawable.product_item_discount_background)
                    name.setTextColor(getColor(itemView.context, R.color.dark_purple))
                } else {
                    itemFoodCategoryBack.setBackgroundResource(R.drawable.category_item_white)
                    name.setTextColor(getColor(itemView.context, R.color.light_purple))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}
