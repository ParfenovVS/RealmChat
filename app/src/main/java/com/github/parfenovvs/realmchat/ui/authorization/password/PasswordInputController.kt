package com.github.parfenovvs.realmchat.ui.authorization.password

import android.view.View
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.NavigationController
import com.github.parfenovvs.realmchat.ui.simple_input.SimpleInputController
import com.github.parfenovvs.realmchat.util.bind


class PasswordInputController : SimpleInputController<PasswordInputView, PasswordInputPresenter>(), PasswordInputView {
    private lateinit var progressView: View
    private var isLogin = false

    companion object {
        fun newInstance(isLogin: Boolean): PasswordInputController {
            val controller = PasswordInputController()
            controller.isLogin = isLogin
            return controller
        }
    }

    override fun onViewCreated(view: View) {
        progressView = bind(view, R.id.progress_view)

        resources?.apply {
            toolbar.title = getString(R.string.password)
            inputField.hint = getString(R.string.your_password)
        }
    }

    override fun createPresenter(): PasswordInputPresenter {
        val emptyPasswordError = resources?.getString(R.string.input_password)
        val shortPasswordError = resources?.getString(R.string.short_password)
        val incorrectEmailOrPasswordError = resources?.getString(R.string.incorrect_email_or_password)
        return PasswordInputPresenter(emptyPasswordError, shortPasswordError, incorrectEmailOrPasswordError)
    }

    override fun onDoneClick() {
        val result = presenter.validateInput(inputField.text.toString())
        if (result != null) {
            showError(result)
        } else if (isLogin) {
            presenter.login()
        } else {
            presenter.registration()
        }
    }

    override fun showLoading() {
        inputField.isEnabled = false
        progressView.visibility = View.VISIBLE
    }

    override fun successLogin() {
        router.pushController(RouterTransaction.with(NavigationController.newInstance(!isLogin))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }

    override fun showError(error: String) {
        inputField.isEnabled = true
        progressView.visibility = View.GONE
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }
}