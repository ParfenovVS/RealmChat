package com.github.parfenovvs.realmchat.util

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bluelinelabs.conductor.Controller


fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) as T }
}

fun <T : View> Controller.bind(view: View, @IdRes res: Int): T {
    @Suppress("UNCHECKED_CAST")
    return view.findViewById<T>(res)
}

fun <T : View> RecyclerView.ViewHolder.bind(view: View, @IdRes res: Int): T {
    @Suppress("UNCHECKED_CAST")
    return view.findViewById<T>(res)
}