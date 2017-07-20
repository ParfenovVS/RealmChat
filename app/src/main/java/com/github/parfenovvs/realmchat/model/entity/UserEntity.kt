package com.github.parfenovvs.realmchat.model.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class UserEntity() : RealmObject() {
    @PrimaryKey
    var id: String? = null
    var email: String? = null
    var name: String? = null

    constructor(profile: UserProfile) : this() {
        this.id = profile.id
        this.email = profile.email
        this.name = profile.name
    }
}