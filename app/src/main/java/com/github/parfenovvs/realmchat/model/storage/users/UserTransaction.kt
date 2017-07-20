package com.github.parfenovvs.realmchat.model.storage.users

import com.github.parfenovvs.realmchat.model.entity.UserEntity
import com.github.parfenovvs.realmchat.model.storage.RealmTransaction
import io.realm.Realm


class UserTransaction(realm: Realm) : RealmTransaction<UserEntity>(realm)