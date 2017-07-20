package com.github.parfenovvs.realmchat.ui.authorization.password

import android.util.Log
import com.github.parfenovvs.realmchat.RealmChatApplication
import com.github.parfenovvs.realmchat.model.UserManager
import com.github.parfenovvs.realmchat.model.entity.UserAuthData
import com.github.parfenovvs.realmchat.model.entity.UserProfile
import com.github.parfenovvs.realmchat.util.ViewState
import io.reactivex.Observable
import javax.inject.Inject


class PasswordInputInteractor {
    @Inject lateinit var userManager: UserManager
    @Inject lateinit var user: UserAuthData

    init {
        RealmChatApplication.appComponent.inject(this)
    }

    fun login(isCreateUser: Boolean): Observable<ViewState<UserProfile>> {
        val profileObservable: Observable<ViewState<UserProfile>> =
                userManager.login(user.email!!, user.password!!, isCreateUser)
                        .map { ViewState.Result(it) }

        return Observable.concat(
                Observable.just(ViewState.Loading()),
                profileObservable
                        .onErrorReturn {
                            Log.e(javaClass.simpleName, Log.getStackTraceString(it))
                            ViewState.Error(it)
                        }
        )
    }
}