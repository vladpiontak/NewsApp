package com.vlad.newsapp.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vlad.newsapp.R

@BindingAdapter("app:set_image_by_url")
fun setImageByUrl(itemView: ImageView, url: String?) {
    Picasso.get().load(url)
        .placeholder(R.drawable.img)
        .error(R.drawable.img)
        .into(itemView)
}