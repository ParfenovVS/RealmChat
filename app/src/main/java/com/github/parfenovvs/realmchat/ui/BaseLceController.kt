package com.github.parfenovvs.realmchat.ui

import android.view.View
import com.github.parfenovvs.realmchat.util.Animator
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.conductor.lce.MvpLceController
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView


abstract class BaseLceController<CV : View, M, V : MvpLceView<M>, P : MvpPresenter<V>> : MvpLceController<CV, M, V, P>() {
    override fun animateContentViewIn() {
        Animator.showContent(loadingView, contentView, errorView)
    }
}