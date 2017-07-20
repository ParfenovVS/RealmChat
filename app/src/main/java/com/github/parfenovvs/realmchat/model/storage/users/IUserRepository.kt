package com.github.parfenovvs.realmchat.model.storage.users

import com.github.parfenovvs.realmchat.model.entity.UserEntity
import io.reactivex.Observable


interface IUserRepository {
    fun getUsers(): Observable<List<UserEntity>>
}