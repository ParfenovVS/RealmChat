package com.github.parfenovvs.realmchat.ui.users

import com.github.parfenovvs.realmchat.model.entity.UserEntity


class UserViewModel(val id: String?, var name: String?) {
    constructor(user: UserEntity) : this(user.id, user.name)
}