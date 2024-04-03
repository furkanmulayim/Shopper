package com.furkanmulayim.shopper.ui.category.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.ItemProductBinding
import com.furkanmulayim.shopper.utils.onSingleClickListener
import com.furkanmulayim.shopper.utils.viewGone
import com.furkanmulayim.shopper.utils.viewVisible
import com.google.android.material.imageview.ShapeableImageView

class CategoryProductAdapter(
    val context: Context,
    private val dataList: ArrayList<ProductItem>,
    private val onClickItem: (ProductItem) -> (Unit),
    private val onClickVariants: (String) -> (Unit)
) : RecyclerView.Adapter<CategoryProductAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.productImage
        private val newText: TextView = binding.productNew
        private val discountDescription: TextView = binding.productDiscountDescription
        private val oldPrica: TextView = binding.productOldPrice
        private val currentPrice: TextView = binding.productCurrentPrice
        val name: TextView = binding.productName
        val colorVariants: TextView = binding.colorVariants
        val kargoLayout: LinearLayout = binding.kargoLayout
        val indirimLayout: LinearLayout = binding.indirimLayout
        private val indirimYuzde: TextView = binding.indirimYuzde

        fun bind(item: ProductItem, context: Context) {
            // todo aktif olmayan ürünler filtrelenip gelecek !!

            //yeni yazısı
            if (item.lojik.isYeni) newText.text =
                ContextCompat.getString(context, R.string.news)
            else viewGone(newText)

            //kargo layoutu kalkabilir
            if (item.lojik.isKargoUcret) viewVisible(kargoLayout)
            else viewGone(kargoLayout)

            discountDescription.text = item.indirimAciklama
            currentPrice.text = item.fiyat.gecerliFiyat
            oldPrica.text = item.fiyat.oncekiFiyat
            name.text = item.isim
            oldPrica.paintFlags = oldPrica.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            val renkSecenek = item.renkSecenek.split(",")
            colorVariants.text = renkSecenek.size.toString()

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item, context)

        if (!item.lojik.isAktif) {
            println("Doldurulacak Burası")
            //notifyItemRemoved(position)
        }

        holder.itemView.onSingleClickListener {
            onClickItem(item)
        }

        holder.colorVariants.onSingleClickListener {

            val renkSecenek = item.renkSecenek.split(",")
            onClickVariants("Varyant: ${renkSecenek.size}")
        }

        holder.indirimLayout.onSingleClickListener {}
        holder.kargoLayout.onSingleClickListener {}
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<ProductItem>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
