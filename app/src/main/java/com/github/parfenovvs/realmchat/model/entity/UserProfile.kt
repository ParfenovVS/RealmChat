package com.github.parfenovvs.realmchat.model.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class UserProfile() : RealmObject() {
    @PrimaryKey
    var id: String? = null
    var email: String? = null
    var name: String? = null

    constructor(id: String?, email: String?, name: String?) : this() {
        this.id = id
        this.email = email
        this.name = name
    }
}
