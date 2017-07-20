package com.github.parfenovvs.realmchat.ui.users

import android.util.Log
import com.github.parfenovvs.realmchat.RealmChatApplication
import com.github.parfenovvs.realmchat.ui.BasePresenter
import com.github.parfenovvs.realmchat.util.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UsersPresenter : BasePresenter<UsersView>() {
    @Inject lateinit var interactor: UsersInteractor

    init {
        RealmChatApplication.appComponent.inject(this)
    }

    override fun attachView(view: UsersView?) {
        super.attachView(view)
        loadUsers()
    }

    fun loadUsers() {
        addDisposable(interactor.users()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it) {
                        is ViewState.Loading -> {
                            view.showLoading(false)
                            Log.d(javaClass.simpleName, "loading")
                        }
                        is ViewState.Result -> {
                            view.setData(it.result)
                            view.showContent()
                            Log.d(javaClass.simpleName, "result")
                        }
                        is ViewState.Error -> {
                            view.showError(it.error, false)
                            Log.e(javaClass.simpleName, Log.getStackTraceString(it.error))
                        }
                    }
                }
        )
    }
}