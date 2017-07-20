package com.github.parfenovvs.realmchat.model

import com.github.parfenovvs.realmchat.BuildConfig
import com.github.parfenovvs.realmchat.model.entity.UserEntity
import com.github.parfenovvs.realmchat.model.entity.UserProfile
import io.reactivex.Observable
import io.realm.Realm
import io.realm.SyncConfiguration
import io.realm.SyncCredentials
import io.realm.SyncUser


class UserManager {
    val AUTH_URL = "http://" + BuildConfig.OBJECT_SERVER_IP + "/auth"
    val REALM_URL = "realm://" + BuildConfig.OBJECT_SERVER_IP + "/~/realm_chat"

    fun login(email: String, password: String, isCreateUser: Boolean = false): Observable<UserProfile> {
        return Observable.create { emitter ->
            run {
                try {
                    SyncUser.login(SyncCredentials.usernamePassword(email, password, isCreateUser), AUTH_URL)

                    setUpConfiguration(SyncUser.currentUser())

                    val profile = if (isCreateUser) {
                        createUser(email, SyncUser.currentUser())
                    } else {
                        getUserProfile()
                    }

                    emitter.onNext(profile)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(UserManagerException("Registration failed", e))
                }
            }
        }
    }

    private fun getUserProfile(): UserProfile {
        val realm = Realm.getDefaultInstance()

        var profile = realm.where(UserProfile::class.java)
                .equalTo("id", SyncUser.currentUser().identity)
                .findFirst() ?: throw UserManagerException("User not found")

        profile = realm.copyFromRealm(profile)

        return profile
    }

    private fun setUpConfiguration(user: SyncUser) {
        val defaultConfig = SyncConfiguration.Builder(user, REALM_URL)
                .waitForInitialRemoteData()
                .build()
        Realm.setDefaultConfiguration(defaultConfig)
        Realm.getInstance(defaultConfig)
    }

    private fun createUser(email: String, user: SyncUser): UserProfile {
        val realm = Realm.getDefaultInstance()

        val profile = UserProfile(user.identity, email, email.split("@")[0])
        realm.executeTransaction {
            it.copyToRealmOrUpdate(profile)
            it.copyToRealmOrUpdate(UserEntity(profile))
        }
        realm.close()

        return profile
    }
}