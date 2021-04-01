package com.example.android.customkeyboardapp.util

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar

internal class NonDismissibleBehavior : BaseTransientBottomBar.Behavior() {

    override fun canSwipeDismissView(child: View): Boolean = false
}