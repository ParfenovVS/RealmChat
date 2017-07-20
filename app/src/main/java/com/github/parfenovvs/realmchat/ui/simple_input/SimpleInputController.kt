package com.github.parfenovvs.realmchat.ui.simple_input

import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.github.parfenovvs.realmchat.R
import com.github.parfenovvs.realmchat.ui.BaseController
import com.github.parfenovvs.realmchat.util.bind


abstract class SimpleInputController<V : SimpleInputView, P : SimpleInputPresenter<V>> : BaseController<V, P>(), SimpleInputView {
    protected lateinit var toolbar: Toolbar
    protected lateinit var inputField: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val v = inflater.inflate(R.layout.controller_simple_input, container, false)

        toolbar = bind(v, R.id.toolbar)
        inputField = bind(v, R.id.input_field)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { router.handleBack() }
        toolbar.inflateMenu(R.menu.done_menu)
        toolbar.setOnMenuItemClickListener {
            onDoneClick()
            true
        }

        onViewCreated(v)

        return v
    }

    open fun onViewCreated(view: View) {
        //Stub!
    }

    override fun clear() {
        inputField.setText("")
    }

    abstract fun onDoneClick()
}