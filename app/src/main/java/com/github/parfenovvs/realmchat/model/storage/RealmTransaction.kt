package com.github.parfenovvs.realmchat.model.storage

import io.realm.Realm
import io.realm.RealmObject


open class RealmTransaction<in E : RealmObject>(val realm: Realm) {
    fun addOrUpdate(o: E) {
        realm.copyToRealmOrUpdate(o)
    }

    fun addOrUpdate(objects: Iterable<E>) {
        realm.copyToRealmOrUpdate(objects)
    }

    open fun deleteAll() {
        realm.deleteAll()
    }
}