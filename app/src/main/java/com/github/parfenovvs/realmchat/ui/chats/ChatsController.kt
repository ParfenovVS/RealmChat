package com.github.parfenovvs.realmchat.ui.chats

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.parfenovvs.realmchat.ui.BaseLceController


class ChatsController : BaseLceController<RecyclerView, List<ChatViewModel>, ChatsView, ChatsPresenter>() {

    companion object {
        fun newInstance() = ChatsController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData(pullToRefresh: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): ChatsPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setData(data: List<ChatViewModel>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}