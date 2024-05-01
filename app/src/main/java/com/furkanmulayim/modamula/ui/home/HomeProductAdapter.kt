package com.furkanmulayim.modamula.ui.home

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.ItemProductBinding
import com.furkanmulayim.modamula.utils.discountCalculate
import com.furkanmulayim.modamula.utils.loadImage
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.stringToList
import com.furkanmulayim.modamula.utils.viewVisible
import com.google.android.material.imageview.ShapeableImageView

class HomeProductAdapter(
    private val mcontext: Context,
    private val dataList: ArrayList<Product>,
    private val onClickItem: (Product) -> (Unit),
    private val onClickVariants: (Product) -> (Unit)
) : RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image: ShapeableImageView = binding.productImage
        private val itemButton: ConstraintLayout = binding.itemFoodCategoryBack
        private val newText: TextView = binding.productNew
        private val discountDescription: TextView = binding.productDiscountDescription
        private val oldPrice: TextView = binding.productOldPrice
        private val newPrice: TextView = binding.productCurrentPrice
        private val name: TextView = binding.productName
        private val colorVariants: TextView = binding.colorVariants
        private val kargoPice: TextView = binding.kargo
        private val indirimLayout: LinearLayout = binding.indirimLayout
        private val indirimYuzde: TextView = binding.indirimYuzde

        fun bind(item: Product) {

            if (item.new == 1) viewVisible(newText)

            val firstImage = item.image?.split(",")?.get(0)
            if (firstImage != null) {
                image.loadImage(firstImage, R.drawable.png_failed)
            }

            if (item.cargoPrice == 0) {
                kargoPice.text = mcontext.getString(R.string.cargo_not_free)
            } else {
                kargoPice.text = mcontext.getString(R.string.cargo_free)
            }

            val currentPriceText = item.currentPrice + "₺"
            val oldPriceText = item.beforePrice + "₺"
            val discountPercentage = item.beforePrice?.toDoubleOrNull()?.let {
                item.currentPrice?.toDoubleOrNull()?.let { it1 -> discountCalculate(it, it1) }
            }

            discountDescription.text = item.discDesc
            indirimYuzde.text = discountPercentage
            newPrice.text = currentPriceText
            oldPrice.text = oldPriceText
            name.text = item.name
            oldPrice.paintFlags = oldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            colorVariants.text = stringToList(item.compatibleSize.toString()).size.toString()

            itemButton.onSingleClickListener { onClickItem(item) }

            colorVariants.onSingleClickListener {
                onClickVariants(item)
            }

            indirimLayout.onSingleClickListener {}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(newList: ArrayList<Product>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
