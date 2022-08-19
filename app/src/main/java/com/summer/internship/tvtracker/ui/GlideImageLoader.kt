package com.summer.internship.tvtracker.ui

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader: ImageLoader {
    override fun loadImage(view: View, url: String, img: ImageView) {
        Glide.with(view)
            .load(url)
            .into(img)
    }
}