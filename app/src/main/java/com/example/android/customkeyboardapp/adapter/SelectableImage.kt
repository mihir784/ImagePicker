package com.example.android.customkeyboardapp.adapter

import android.net.Uri

data class SelectableImage(
    val id: Int,
    val uri: Uri,
    val selected: Boolean
)