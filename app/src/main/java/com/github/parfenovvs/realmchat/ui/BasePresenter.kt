package com.github.parfenovvs.realmchat.ui

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<V : MvpView> : MvpBasePresenter<V>() {
    private val disposables = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun detachView(retainInstance: Boolean) {
        disposables.clear()
        super.detachView(retainInstance)
    }
}