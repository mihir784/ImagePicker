package com.example.android.customkeyboardapp

import com.example.android.customkeyboardapp.loader.ImageLoader

class CustomPhotoPicker {

    fun init(loader: ImageLoader, authority: String? = null) {
        PickerConfiguration.setUp(loader, authority)
    }
}