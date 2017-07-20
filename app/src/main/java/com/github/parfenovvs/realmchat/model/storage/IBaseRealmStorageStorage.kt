package com.github.parfenovvs.realmchat.model.storage

import io.realm.RealmObject


interface IBaseRealmStorageStorage<in E : RealmObject, out T : RealmTransaction<E>> {
    /**
     * Automatic closes current realm
     */
    fun transaction(transactionBody: (T) -> Unit)

    fun <E : RealmObject> copyFromRealm(e: E): E

    fun <E : RealmObject> copyFromRealm(iterable: Iterable<E>): List<E>

    fun close()
}