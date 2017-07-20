package com.github.parfenovvs.realmchat.ui.authorization.email

import android.view.View
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.authorization.password.PasswordInputController
import com.github.parfenovvs.realmchat.ui.simple_input.SimpleInputController


class EmailInputController : SimpleInputController<EmailInputView, EmailInputPresenter>(), EmailInputView {
    private var isLogin: Boolean = false

    companion object {
        fun newInstance(isLogin: Boolean): EmailInputController {
            val controller = EmailInputController()
            controller.isLogin = isLogin
            return controller
        }
    }

    override fun onViewCreated(view: View) {
        resources?.apply {
            toolbar.title = getString(R.string.email)
            inputField.hint = getString(R.string.email_hint)
        }
    }

    override fun createPresenter(): EmailInputPresenter {
        val emptyEmailError = resources?.getString(R.string.input_email)
        val wrongEmailError = resources?.getString(R.string.wrong_email)
        return EmailInputPresenter(emptyEmailError, wrongEmailError)
    }

    override fun onDoneClick() {
        val result = presenter.validateInput(inputField.text.toString())
        if (result != null) {
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        } else goToTypePassword()
    }

    private fun goToTypePassword() {
        router.pushController(RouterTransaction.with(PasswordInputController.newInstance(isLogin))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }
}