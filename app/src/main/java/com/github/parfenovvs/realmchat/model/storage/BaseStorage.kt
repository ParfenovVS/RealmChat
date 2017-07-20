package com.github.parfenovvs.realmchat.model.storage

import io.realm.Realm
import io.realm.RealmObject


abstract class BaseStorage<in E : RealmObject, out T : RealmTransaction<E>>(private val transactionBuilder: (Realm) -> T) :
        IBaseRealmStorageStorage<E, T> {
    val realm = Realm.getDefaultInstance()
    val isClosed: Boolean
        get() = realm.isClosed

    override fun transaction(transactionBody: (T) -> Unit) {
        realm.executeTransaction { transactionBody.invoke(transactionBuilder(realm)) }
        close()
    }

    override fun <E : RealmObject> copyFromRealm(e: E): E {
        return realm.copyFromRealm(e)
    }

    override fun <E : RealmObject> copyFromRealm(iterable: Iterable<E>): List<E> {
        return realm.copyFromRealm(iterable)
    }

    override fun close() {
        if (!isClosed) realm.close()
    }
}