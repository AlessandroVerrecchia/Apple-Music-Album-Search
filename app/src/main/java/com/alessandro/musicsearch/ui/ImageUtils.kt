package com.alessandro.musicsearch.ui

import android.widget.ImageView
import com.alessandro.musicsearch.R
import com.bumptech.glide.Glide

fun loadImage(imageView: ImageView?, url: String?) {
    imageView ?: return
    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.cd_logo)
        .into(imageView)
}