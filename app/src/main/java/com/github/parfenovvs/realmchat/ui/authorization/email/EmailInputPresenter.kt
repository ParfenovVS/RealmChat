package com.github.parfenovvs.realmchat.ui.authorization.email

import android.util.Patterns
import com.github.parfenovvs.realmchat.RealmChatApplication
import com.github.parfenovvs.realmchat.model.entity.UserAuthData
import com.github.parfenovvs.realmchat.ui.simple_input.SimpleInputPresenter
import javax.inject.Inject


class EmailInputPresenter(val emptyEmailError: String?,
                          val wrongEmailError: String?) :
        SimpleInputPresenter<EmailInputView>() {

    @Inject lateinit var user: UserAuthData

    init {
        RealmChatApplication.appComponent.inject(this)
    }

    override fun validateInput(text: String): String? {
        val trimmedText = text.trim()

        return if (trimmedText.isEmpty()) {
            view.clear()
            emptyEmailError
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trimmedText).matches()) {
            wrongEmailError
        } else {
            user.email = trimmedText
            null
        }
    }

}