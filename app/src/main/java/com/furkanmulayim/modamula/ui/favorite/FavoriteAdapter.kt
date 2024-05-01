package com.furkanmulayim.modamula.ui.favorite

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.ItemProductFavoriBinding
import com.furkanmulayim.modamula.utils.discountCalculate
import com.furkanmulayim.modamula.utils.loadImage
import com.furkanmulayim.modamula.utils.onSingleClickListener

class FavoriteAdapter(
    private val dataList: ArrayList<Product>,
    val onClickDeleteItem: (Int) -> (Unit),
    private val onClickItem: (Product) -> (Unit),
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


    inner class ViewHolder(binding: ItemProductFavoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val detailButton = binding.productDetailButton
        private val buyButton = binding.productBuyButton
        private val deleteButton = binding.deleteButton
        private val itemButton = binding.itemFoodCategoryBack

        private val name = binding.productName
        private val desc = binding.productDescription
        private val old = binding.productOldPrice
        private val curr = binding.productCurrentPrice
        private val nums = binding.productSize
        private val img = binding.shapeableImageView
        private val discPercentage = binding.indirimYuzde

        fun bind(item: Product) {

            val firstImage = item.image?.split(",")?.get(0)
            if (firstImage != null) {
                img.loadImage(firstImage, R.drawable.png_failed)
            }

            val currentPriceText = item.currentPrice + "₺"
            val oldPriceText = item.beforePrice + "₺"
            val discountPercentage = item.beforePrice?.toDoubleOrNull()?.let {
                item.currentPrice?.toDoubleOrNull()?.let { it1 -> discountCalculate(it, it1) }
            }

            name.text = item.name
            desc.text = item.description
            old.text = oldPriceText
            curr.text = currentPriceText
            old.paintFlags = old.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            discPercentage.text = discountPercentage
            nums.text = item.compatibleSize

            buyButton.onSingleClickListener {}
            detailButton.onSingleClickListener { onClickItem(item) }
            itemButton.onSingleClickListener { onClickItem(item) }
            deleteButton.onSingleClickListener { item.id?.let { it1 -> onClickDeleteItem(it1) } }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductFavoriBinding.inflate(
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
    fun updateList(newList: ArrayList<Product>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }
}
