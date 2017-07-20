package com.github.parfenovvs.realmchat.model.storage.users

import com.github.parfenovvs.realmchat.model.entity.UserEntity
import io.reactivex.Observable


class UserRepository : IUserRepository {
    override fun getUsers(): Observable<List<UserEntity>> {
        return Observable.defer {
            val storage = UserStorage()
            val users = storage.getUsers()
            storage.close()
            Observable.just(users)
        }
    }
}