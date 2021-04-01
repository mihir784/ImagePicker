package com.example.android.customkeyboardapp.picker

import com.example.android.customkeyboardapp.loaders.ImageLoader

class CustomPhotoPicker {

    fun init(loader: ImageLoader, authority: String? = null) {
        PickerConfiguration.setUp(loader, authority)
    }
}