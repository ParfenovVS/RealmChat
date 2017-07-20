package com.github.parfenovvs.realmchat.model.storage.users

import com.github.parfenovvs.realmchat.model.entity.UserEntity
import com.github.parfenovvs.realmchat.model.storage.BaseStorage


class UserStorage : BaseStorage<UserEntity, UserTransaction>({ UserTransaction(it) }) {
    companion object {
        private val KEY_NAME = "name"
    }

    fun getUsers(): List<UserEntity> {
        return copyFromRealm(
                realm.where(UserEntity::class.java)
                        .findAllSorted(KEY_NAME)
        )
    }
}