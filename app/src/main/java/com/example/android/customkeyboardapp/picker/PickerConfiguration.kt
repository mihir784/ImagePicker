package com.example.android.customkeyboardapp.picker

import com.example.android.customkeyboardapp.loaders.ImageLoader

internal object PickerConfiguration {

    private var imageLoader: ImageLoader? = null
    private var authority: String? = null

    fun setUp(imageLoader: ImageLoader, authority: String?) {
        PickerConfiguration.imageLoader = imageLoader
        PickerConfiguration.authority = authority
    }

    fun getImageLoader(): ImageLoader =
        imageLoader ?: throw IllegalStateException("ImageLoader is null. You probably forget to call ChiliPhotoPicker.init()")

    fun getAuthority(): String =
        authority ?: throw IllegalStateException("Authority is null. You probably forget to pass it to ChiliPhotoPicker.init()")
}