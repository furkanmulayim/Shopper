package com.furkanmulayim.shopper.ui.home

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.ItemProductBinding
import com.google.android.material.imageview.ShapeableImageView

class HomeProductAdapter(
    private val context: Context,
    private val dataList: ArrayList<ProductItem>,
    private val onClick: (String) -> (Unit)
) : RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {

    private var lastAddedItemPosition: Int = -1


    class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.productImage
        val newText: TextView = binding.productNew
        val discountDescription: TextView = binding.productDiscountDescription
        val oldPrica: TextView = binding.productOldPrice
        val currentPrice: TextView = binding.productCurrentPrice
        val name: TextView = binding.productName

        fun bind(item: ProductItem) {
            newText.text = item.yeniMi.toString()
            discountDescription.text = item.indirimAciklama
            currentPrice.text = item.yeniFiyat
            oldPrica.text = item.eskiFiyat
            name.text = item.adi
            oldPrica.paintFlags = oldPrica.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
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

    fun updateList(newList: List<ProductItem>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyItemRangeChanged(0, newList.size)
        }
    }

}
