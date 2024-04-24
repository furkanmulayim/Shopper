package com.furkanmulayim.shopper.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.model.ProductCategory
import com.furkanmulayim.shopper.databinding.ItemProductCategoryBinding

class CategoryAdapter(
    private val dataList: ArrayList<ProductCategory>,
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
                    onClick(dataList[position].name)
                }
            }
        }

        fun bind(item: ProductCategory) {
            binding.apply {
                name.text = item.name
                logo.setBackgroundResource(item.image)

                if (adapterPosition == selectedPosition) {
                    itemFoodCategoryBack.setBackgroundResource(R.drawable.product_item_discount_background)
                    name.setTextColor(getColor(itemView.context, R.color.white))
                    logo.setColorFilter(
                        getColor(itemView.context, R.color.dark_purple),
                        android.graphics.PorterDuff.Mode.SRC_OUT
                    )
                } else {
                    itemFoodCategoryBack.setBackgroundResource(R.drawable.category_item_white)
                    name.setTextColor(getColor(itemView.context, R.color.dark_purple))
                    logo.setColorFilter(
                        getColor(itemView.context, R.color.hint_text),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
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
