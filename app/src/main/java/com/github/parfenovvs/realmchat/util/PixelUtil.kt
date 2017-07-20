package com.github.parfenovvs.realmchat.util

import android.content.Context
import android.util.DisplayMetrics


object PixelUtil {
    fun dpToPx(context: Context?, dp: Float): Int {
        if (context != null) {
            return Math.round(dp * getPixelScaleFactor(context))
        }
        return 0
    }

    private fun getPixelScaleFactor(context: Context?): Float {
        if (context != null) {
            val appContext = context.applicationContext
            val displayMetrics = appContext.resources.displayMetrics
            return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
        }
        return 0f
    }
}