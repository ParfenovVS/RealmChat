package com.github.parfenovvs.realmchat.ui.authorization.password

import com.github.parfenovvs.realmchat.RealmChatApplication
import com.github.parfenovvs.realmchat.model.entity.UserAuthData
import com.github.parfenovvs.realmchat.ui.simple_input.SimpleInputPresenter
import com.github.parfenovvs.realmchat.util.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PasswordInputPresenter(val emptyPasswordError: String?,
                             val shortPasswordError: String?,
                             val incorrectEmailOrPassword: String?) :
        SimpleInputPresenter<PasswordInputView>() {
    companion object {
        private val PASSWORD_MIN_LENGTH = 6
    }

    @Inject lateinit var user: UserAuthData
    @Inject lateinit var interactor: PasswordInputInteractor

    init {
        RealmChatApplication.appComponent.inject(this)
    }

    override fun validateInput(text: String): String? {
        return if (text.isEmpty()) emptyPasswordError
        else if (text.length < PASSWORD_MIN_LENGTH) shortPasswordError
        else {
            user.password = text
            null
        }
    }

    fun login() {
        login(false)
    }

    fun registration() {
        login(true)
    }

    private fun login(isCreateUser: Boolean) {
        addDisposable(interactor.login(isCreateUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it) {
                        is ViewState.Loading -> view.showLoading()
                        is ViewState.Result -> view.successLogin()
                        is ViewState.Error -> incorrectEmailOrPassword?.let { view.showError(it) }
                    }
                }, { })
        )
    }

}