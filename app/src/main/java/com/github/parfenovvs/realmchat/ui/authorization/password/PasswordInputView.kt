package com.github.parfenovvs.realmchat.ui.authorization.password

import com.github.parfenovvs.realmchat.ui.simple_input.SimpleInputView


interface PasswordInputView : SimpleInputView {
    fun showLoading()
    fun successLogin()
    fun showError(error: String)
}