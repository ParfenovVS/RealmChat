package com.github.parfenovvs.realmchat

import android.app.Application
import com.github.parfenovvs.realmchat.di.AppComponent
import com.github.parfenovvs.realmchat.di.DaggerAppComponent
import com.github.parfenovvs.realmchat.di.RealmChatModule
import io.realm.Realm
import io.realm.log.LogLevel
import io.realm.log.RealmLog


class RealmChatApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        RealmLog.setLevel(LogLevel.DEBUG)

        buildComponent()
    }

    private fun buildComponent() {
        appComponent = DaggerAppComponent.builder()
                .realmChatModule(RealmChatModule())
                .build()
    }
}