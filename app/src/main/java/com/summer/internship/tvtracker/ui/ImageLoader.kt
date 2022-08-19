package com.summer.internship.tvtracker.ui

import android.view.View
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: View, url: String, img: ImageView)
}