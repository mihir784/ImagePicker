package com.example.android.customkeyboardapp.util

import android.content.Context
import androidx.core.content.FileProvider
import com.example.android.customkeyboardapp.picker.PickerConfiguration
import java.io.File

internal fun File.providerUri(context: Context) =
    FileProvider.getUriForFile(context, PickerConfiguration.getAuthority(), this)