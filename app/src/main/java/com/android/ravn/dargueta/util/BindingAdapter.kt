package com.android.ravn.dargueta.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:visibleIfNotNullNorEmpty")
    fun TextView.visibleIfNotNullNorEmpty(text: String?) {
        if (text.isNullOrBlank()) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
            this.text = text
        }
    }

}