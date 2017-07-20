package com.github.parfenovvs.realmchat.ui.authorization

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.authorization.email.EmailInputController
import com.github.parfenovvs.realmchat.util.bind


class StartController : Controller() {
    private lateinit var loginButton: View
    private lateinit var registrationButton: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val v = inflater.inflate(R.layout.controller_start, container, false)

        loginButton = bind(v, R.id.login_button)
        registrationButton = bind(v, R.id.registration_button)

        loginButton.setOnClickListener { onLoginClick() }
        registrationButton.setOnClickListener { onRegistrationClick() }

        return v
    }

    private fun onLoginClick() {
        goToController(EmailInputController.newInstance(true))
    }

    private fun onRegistrationClick() {
        goToController(EmailInputController.newInstance(false))
    }

    private fun goToController(controller: Controller) {
        router.pushController(RouterTransaction.with(controller)
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }
}