package com.furkanmulayim.shopper.ui.category.adapters

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

    private var lastAddedItemPosition: Int = -1


    class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ShapeableImageView = binding.productImage
        val newText: TextView = binding.productNew
        val discountDescription: TextView = binding.productDiscountDescription
        val oldPrica: TextView = binding.productOldPrice
        val currentPrice: TextView = binding.productCurrentPrice
        val name: TextView = binding.productName
        val colorVariants: TextView = binding.colorVariants
        val kargoLayout: LinearLayout = binding.kargoLayout
        val indirimLayout: LinearLayout = binding.indirimLayout
        val indirimYuzde: TextView = binding.indirimYuzde

        fun bind(item: ProductItem, context: Context) {
            // todo aktif olmayan ürünler filtrelenip gelecek !!

            //yeni yazısı
            if (item.lojik.isYeni) newText.text =
                ContextCompat.getString(context, R.string.news)
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
            //notifyItemRemoved(position)
        }

        holder.itemView.onSingleClickListener {
            onClickItem(item)
        }

        holder.colorVariants.onSingleClickListener {
            onClickVariants("Varyant: ${item.renkSecenek.size}")
        }

        holder.indirimLayout.onSingleClickListener {}
        holder.kargoLayout.onSingleClickListener {}
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(newList: ArrayList<ProductItem>?) {
        if (newList != null) {
            dataList.clear()
            dataList.addAll(newList)
            notifyDataSetChanged()
        }
    }

}
