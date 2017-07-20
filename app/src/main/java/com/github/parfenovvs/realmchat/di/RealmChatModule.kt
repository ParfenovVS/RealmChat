package com.github.parfenovvs.realmchat.di

import android.support.annotation.NonNull
import com.github.parfenovvs.realmchat.model.UserManager
import com.github.parfenovvs.realmchat.model.entity.UserAuthData
import com.github.parfenovvs.realmchat.model.storage.users.IUserRepository
import com.github.parfenovvs.realmchat.model.storage.users.UserRepository
import com.github.parfenovvs.realmchat.ui.authorization.password.PasswordInputInteractor
import com.github.parfenovvs.realmchat.ui.users.UsersInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RealmChatModule {
    @Provides
    @NonNull
    @Singleton
    fun provideUser() = UserAuthData()

    @Provides
    @NonNull
    @Singleton
    fun provideUserManager() = UserManager()

    @Provides
    @NonNull
    fun providePasswordInputInteractor() = PasswordInputInteractor()

    @Provides
    @NonNull
    fun provideUsersInteractor() = UsersInteractor()

    @Provides
    @NonNull
    @Singleton
    fun provideIUserRepository(): IUserRepository {
        return UserRepository()
    }
}