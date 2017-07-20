package com.github.parfenovvs.realmchat.di

import com.github.parfenovvs.realmchat.ui.authorization.email.EmailInputPresenter
import com.github.parfenovvs.realmchat.ui.authorization.password.PasswordInputInteractor
import com.github.parfenovvs.realmchat.ui.authorization.password.PasswordInputPresenter
import com.github.parfenovvs.realmchat.ui.users.UsersInteractor
import com.github.parfenovvs.realmchat.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(RealmChatModule::class))
@Singleton
interface AppComponent {
    fun inject(target: EmailInputPresenter)
    fun inject(target: PasswordInputPresenter)
    fun inject(target: PasswordInputInteractor)
    fun inject(target: UsersPresenter)
    fun inject(target: UsersInteractor)
}