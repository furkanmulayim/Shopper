package com.furkanmulayim.shopper.utils

import OnSingleClickListener
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.furkanmulayim.shopper.R
import es.dmoral.toasty.Toasty

fun ImageView.loadImage(url: String) {
    val opt = RequestOptions().error(R.drawable.ic_launcher_background)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this).waitForLayout()
}

fun viewGone(view: View){
    view.visibility = View.GONE
}

fun viewVisible(view: View){
    view.visibility = View.VISIBLE
}

fun View.onSingleClickListener(listener: View.OnClickListener) {
    this.setOnClickListener(
        object : OnSingleClickListener() {
            override fun onSingleClick(view: View?) {
                listener.onClick(view)
            }
        },
    )
}


fun viewMessage(context: Context, message:String){
    Toasty.custom(
        context,
        message,
        null,
        Toast.LENGTH_SHORT,
        false
    ).show()
}

fun viewErrorMessage(context: Context, message:String){
    Toasty.error(
        context,
        message
    ).show()
}

