package com.example.android.customkeyboardapp.loaders

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader: ImageLoader {

    override fun loadImage(context: Context, view: ImageView, uri: Uri) {
        Glide.with(context)
            .asBitmap()
            .load(uri)
            .centerCrop()
            .into(view)
    }
}