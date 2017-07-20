package com.github.parfenovvs.realmchat.ui.users

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.util.PixelUtil
import com.github.parfenovvs.realmchat.util.bind


class UsersAdapter(context: Context) : RecyclerView.Adapter<UsersAdapter.Companion.VH>() {
    private var items = arrayListOf<UserViewModel>()
    private val textDrawableBuilder: TextDrawable.IBuilder = TextDrawable.builder()
            .beginConfig()
            .fontSize(PixelUtil.dpToPx(context, 24.toFloat()))
            .toUpperCase()
            .textColor(Color.WHITE)
            .endConfig()
            .round()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(textDrawableBuilder,
                LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: VH?, position: Int) {
        holder?.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(data: List<UserViewModel>?) {
        data?.let {
            items.clear()
            items.addAll(data)
        }
    }

    companion object {
        class VH(val avatarBuilder: TextDrawable.IBuilder, itemView: View) : RecyclerView.ViewHolder(itemView) {
            val avatarView: ImageView = bind(itemView, R.id.avatar)
            val nameTextView: TextView = bind(itemView, R.id.name)

            fun bind(item: UserViewModel) {
                avatarView.setImageDrawable(buildTextDrawable(item.id ?: "", item.name ?: "R"))
                nameTextView.text = item.name
            }

            private fun buildTextDrawable(key: Any, title: String): TextDrawable {
                return avatarBuilder.build(title.substring(0, 1).toUpperCase(), ColorGenerator.MATERIAL.getColor(key))
            }
        }
    }
}