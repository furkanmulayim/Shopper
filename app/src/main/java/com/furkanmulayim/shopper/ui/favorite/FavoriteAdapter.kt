package com.furkanmulayim.shopper.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.ItemProductFavoriBinding
import com.furkanmulayim.shopper.utils.onSingleClickListener

class FavoriteAdapter(
    private val context: Context,
    private val dataList: ArrayList<ProductItem>
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemProductFavoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val detailButton = binding.productDetailButton
        private val buyButton = binding.productBuyButton
        fun bind(item: ProductItem) {

            buyButton.onSingleClickListener {}
            detailButton.onSingleClickListener {}

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
}