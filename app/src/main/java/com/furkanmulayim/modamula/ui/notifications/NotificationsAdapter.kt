package com.furkanmulayim.modamula.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.data.enums.Notify
import com.furkanmulayim.modamula.data.model.Notifications
import com.furkanmulayim.modamula.databinding.ItemNotificationsBinding

class NotificationsAdapter(
    private val dataList: ArrayList<Notifications>,
) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemNotificationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val header: TextView = binding.notifyHeader
        private val text: TextView = binding.notifyText
        private val image: ImageView = binding.image

        fun bind(item: Notifications) {

            val imageResource: Int = when (item.type) {
                Notify.COUPON.id -> R.drawable.notify_png_coupon
                Notify.DISCOUNT.id -> R.drawable.notify_png_discount
                Notify.PRIVATE.id -> R.drawable.notify_png_private
                else -> R.drawable.notify_png_private
            }

            image.setBackgroundResource(imageResource)
            header.text = item.head
            text.text = item.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationsBinding.inflate(
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
