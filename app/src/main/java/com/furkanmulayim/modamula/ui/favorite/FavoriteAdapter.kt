package com.furkanmulayim.modamula.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.ItemProductFavoriBinding
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
        fun bind(item: Product) {
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
}
