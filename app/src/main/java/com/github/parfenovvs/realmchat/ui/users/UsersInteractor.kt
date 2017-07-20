package com.github.parfenovvs.realmchat.ui.users

import com.github.parfenovvs.realmchat.RealmChatApplication
import com.github.parfenovvs.realmchat.model.storage.users.IUserRepository
import com.github.parfenovvs.realmchat.util.ViewState
import io.reactivex.Observable
import javax.inject.Inject


class UsersInteractor {
    @Inject lateinit var repository: IUserRepository

    init {
        RealmChatApplication.appComponent.inject(this)
    }

    fun users(): Observable<ViewState<List<UserViewModel>>> {
        val usersObservable = repository.getUsers()
                .map {
                    if (it.isEmpty()) ViewState.Error<List<UserViewModel>>(UsersInteractingException("Users not found"))
                    else ViewState.Result(it.map { UserViewModel(it) })
                }

        return Observable.concat(
                Observable.just(ViewState.Loading()),
                usersObservable.onErrorReturn { ViewState.Error(it) }
        )
    }
}