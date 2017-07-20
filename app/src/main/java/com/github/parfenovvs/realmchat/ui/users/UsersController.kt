package com.github.parfenovvs.realmchat.ui.users

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.BaseLceController
import com.github.parfenovvs.realmchat.util.bind


class UsersController : BaseLceController<RecyclerView, List<UserViewModel>, UsersView, UsersPresenter>(), UsersView {
    lateinit var toolbar: Toolbar
    private var adapter: UsersAdapter? = null

    companion object {
        fun newInstance() = UsersController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val v = inflater.inflate(R.layout.controller_users, container, false)

        toolbar = bind(v, R.id.toolbar)
        val recyclerView = bind<RecyclerView>(v, R.id.contentView)

        toolbar.title = resources?.getString(R.string.users)

        adapter = UsersAdapter(container.context)
        recyclerView.layoutManager = LinearLayoutManager(container.context)
        recyclerView.adapter = adapter

        return v
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return resources?.getString(R.string.users_not_found) ?: "Error"
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadUsers()
    }

    override fun createPresenter() = UsersPresenter()

    override fun setData(data: List<UserViewModel>?) {
        adapter?.setData(data)
        adapter?.notifyDataSetChanged()
    }
}