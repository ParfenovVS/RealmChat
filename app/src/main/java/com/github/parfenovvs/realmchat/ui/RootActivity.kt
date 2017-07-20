package com.github.parfenovvs.realmchat.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.authorization.StartController
import com.github.parfenovvs.realmchat.util.bind
import io.realm.SyncUser

class RootActivity : AppCompatActivity() {
    private val containerView by bind<ViewGroup>(R.id.container)
    private var router: Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        router = Conductor.attachRouter(this, containerView, savedInstanceState)
        if (!router!!.hasRootController()) {
            if (SyncUser.currentUser() != null) {
                router!!.setRoot(RouterTransaction.with(NavigationController()))
            } else {
                router!!.setRoot(RouterTransaction.with(StartController()))
            }
        }
    }

    override fun onBackPressed() {
        if (!router!!.handleBack()) {
            super.onBackPressed()
        }
    }
}
