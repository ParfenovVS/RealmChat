package com.github.parfenovvs.realmchat.ui.simple_input

import com.github.parfenovvs.realmchat.ui.BasePresenter


abstract class SimpleInputPresenter<V : SimpleInputView> : BasePresenter<V>() {
    abstract fun validateInput(text: String): String?
}