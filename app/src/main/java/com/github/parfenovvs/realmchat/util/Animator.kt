package com.github.parfenovvs.realmchat.util

import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.github.parfenovvs.realmchat.R


object Animator {
    fun showContent(loadingView: View, contentView: View, errorView: View) {
        errorView.visibility = View.GONE
        if (contentView.visibility == View.VISIBLE) {
            loadingView.visibility = View.GONE
        } else {
            val resources = loadingView.resources
            val set = AnimatorSet()

            val contentFadeIn = ObjectAnimator.ofFloat(contentView, View.ALPHA, 0f, 1f)
            val loadingFadeOut = ObjectAnimator.ofFloat(loadingView, View.ALPHA, 1f, 0f)

            set.playTogether(contentFadeIn, loadingFadeOut)
            set.duration = resources.getInteger(R.integer.lce_content_view_show_animation_time).toLong()

            set.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    loadingView.visibility = View.GONE
                    loadingView.alpha = 1f
                }

                override fun onAnimationStart(animation: android.animation.Animator) {
                    contentView.visibility = View.VISIBLE
                }
            })

            set.start()
        }
    }
}