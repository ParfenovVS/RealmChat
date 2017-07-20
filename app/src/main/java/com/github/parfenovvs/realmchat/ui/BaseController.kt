package com.github.parfenovvs.realmchat.ui

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController


abstract class BaseController<V : MvpView, P : MvpPresenter<V>> : MvpController<V, P>() {
}