package com.furkanmulayim.modamula.ui.favorite

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.ItemProductFavoriBinding
import com.furkanmulayim.modamula.utils.animFast
import com.furkanmulayim.modamula.utils.discountCalculate
import com.furkanmulayim.modamula.utils.loadImage
import com.furkanmulayim.modamula.utils.onSingleClickListener

class FavoriteAdapter(
    private val dataList: ArrayList<Product>,
    private val onClickDeleteItem: (Int) -> Unit,
    private val onClickItem: (Product) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductFavoriBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                productDetailButton.onSingleClickListener { onClickItem(dataList[adapterPosition]) }
                itemFoodCategoryBack.onSingleClickListener { onClickItem(dataList[adapterPosition]) }
                animFast(itemFoodCategoryBack)
                deleteButton.onSingleClickListener {
                    onClickDeleteItem(dataList[adapterPosition].id ?: -1)
                }
            }
        }

        fun bind(item: Product) {
            binding.apply {
                val firstImage = item.image?.split(",")?.firstOrNull()
                firstImage?.let { shapeableImageView.loadImage(it, R.drawable.png_failed) }

                productName.text = item.name
                productDescription.text = item.description
                val opc = "${item.beforePrice}₺"
                val cpc = "${item.currentPrice}₺"
                productOldPrice.text = opc
                productCurrentPrice.text = cpc
                productOldPrice.paintFlags =
                    productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                productSize.text = item.compatibleSize

                val discountPercentage = item.beforePrice?.toDoubleOrNull()?.let { beforePrice ->
                    item.currentPrice?.toDoubleOrNull()?.let { currentPrice ->
                        discountCalculate(beforePrice, currentPrice)
                    }
                }
                indirimYuzde.text = discountPercentage ?: ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductFavoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun updateList(newList: ArrayList<Product>?) {
        newList?.let {
            dataList.apply {
                clear()
                addAll(it)
            }
            notifyDataSetChanged()
        }
    }
}
