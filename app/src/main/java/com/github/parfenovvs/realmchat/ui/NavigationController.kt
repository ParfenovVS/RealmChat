package com.github.parfenovvs.realmchat.ui

import android.support.design.widget.BottomNavigationView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.chats.ChatsController
import com.github.parfenovvs.realmchat.ui.settings.SettingsController
import com.github.parfenovvs.realmchat.ui.users.UsersController
import com.github.parfenovvs.realmchat.util.bind


class NavigationController : Controller() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var childRouter: Router
    private var isNewUser = false

    companion object {
        fun newInstance(isNewUser: Boolean): NavigationController {
            val controller = NavigationController()
            controller.isNewUser = isNewUser
            return controller
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val v = inflater.inflate(R.layout.controller_navigation, container, false)

        bottomNavigationView = bind(v, R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected)

        childRouter = getChildRouter(bind(v, R.id.navigation_container))
        if (!childRouter.hasRootController()) {
            if (isNewUser) {
                childRouter.setRoot(RouterTransaction.with(UsersController.newInstance()))
                bottomNavigationView.selectedItemId = R.id.item_users
                isNewUser = false
            } else {
                //TODO: chats
                childRouter.setRoot(RouterTransaction.with(UsersController.newInstance()))
                bottomNavigationView.selectedItemId = R.id.item_users
            }
        }

        return v
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        val controller = when (item.itemId) {
            R.id.item_users -> UsersController.newInstance()
            R.id.item_messages -> ChatsController.newInstance()
            R.id.item_settings -> SettingsController.newInstance()
            else -> throw IllegalStateException("Menu item with id ${item.itemId} not found")
        }

        childRouter.setRoot(RouterTransaction.with(controller)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))

        return true
    }
}