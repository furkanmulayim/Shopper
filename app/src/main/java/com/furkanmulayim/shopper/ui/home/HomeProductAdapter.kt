package com.furkanmulayim.shopper.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.ItemProductBinding
import com.furkanmulayim.shopper.utils.onSingleClickListener
import com.furkanmulayim.shopper.utils.viewGone
import com.furkanmulayim.shopper.utils.viewVisible
import com.google.android.material.imageview.ShapeableImageView

class HomeProductAdapter(
    val context: Context,
    private val dataList: ArrayList<ProductItem>,
    private val onClickItem: (ProductItem) -> (Unit),
    private val onClickVariants: (String) -> (Unit)
) : RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image: ShapeableImageView = binding.productImage
        private val itemButton: ConstraintLayout = binding.itemFoodCategoryBack
        private val newText: TextView = binding.productNew
        private val discountDescription: TextView = binding.productDiscountDescription
        private val oldPrica: TextView = binding.productOldPrice
        private val currentPrice: TextView = binding.productCurrentPrice
        private val name: TextView = binding.productName
        private val colorVariants: TextView = binding.colorVariants
        private val kargoLayout: LinearLayout = binding.kargoLayout
        private val indirimLayout: LinearLayout = binding.indirimLayout
        private val indirimYuzde: TextView = binding.indirimYuzde

        fun bind(item: ProductItem, context: Context) {
            // todo aktif olmayan ürünler filtrelenip gelecek !!

            //yeni yazısı
            if (item.lojik.isYeni) newText.text = ContextCompat.getString(context, R.string.news)
            else viewGone(newText)

            //kargo layoutu kalkabilir
            if (item.lojik.isKargoUcret) viewVisible(kargoLayout)
            else viewGone(kargoLayout)

            discountDescription.text = item.indirim.indirimAciklama
            currentPrice.text = item.fiyat.gecerliFiyat
            oldPrica.text = item.fiyat.oncekiFiyat
            name.text = item.isim
            oldPrica.paintFlags = oldPrica.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            colorVariants.text = item.renkSecenek.size.toString()
            indirimYuzde.text = item.indirim.indirimYuzde

            itemButton.onSingleClickListener { onClickItem(item) }

            colorVariants.onSingleClickListener {
                onClickVariants("Varyant: ${item.renkSecenek.size}")
            }
            indirimLayout.onSingleClickListener {}
            kargoLayout.onSingleClickListener {}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        if (item.lojik.isAktif) {
            holder.bind(item, context)
        }
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
