package com.furkanmulayim.shopper.ui.category.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.data.model.ProductCategory
import com.furkanmulayim.shopper.utils.onSingleClickListener

class CategoryAdapter(
    private val context: Context,
    private val dataList: ArrayList<ProductCategory>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.SCROLLBAR_POSITION_DEFAULT

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val back: LinearLayout = itemView.findViewById(R.id.item_food_category_back)
        val front: ImageView = itemView.findViewById(R.id.logo)
        val name: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_category, parent, false)
        return ViewHolder(view)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = dataList[position]

        holder.front.setBackgroundResource(item.image)
        holder.name.text = item.name

        // Tıklanan ögenin pozisyonunu kontrol et
        if (position == selectedPosition) {
            // Tıklanan ögenin arka planını beyaz yap
            holder.back.setBackgroundResource(R.drawable.product_item_discount_background)
            holder.name.setTextColor(getColor(context, R.color.white))
            holder.front.setColorFilter(
                getColor(context, R.color.dark_purple),
                android.graphics.PorterDuff.Mode.SRC_OUT
            )
        } else {
            // Diğer ögelerin arka planını siyah yap
            holder.back.setBackgroundResource(R.drawable.category_item_white)
            holder.name.setTextColor(getColor(context, R.color.dark_purple))
            holder.front.setColorFilter(
                getColor(context, R.color.hint_text),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        holder.itemView.onSingleClickListener {
            selectedPosition = position
            onClick(item.name)
            notifyDataSetChanged() // Veri setini güncelle
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

