package com.android.ravn.dargueta.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Binding : ViewDataBinding>(
    view: View
) : RecyclerView.ViewHolder(view) {

    val binding = DataBindingUtil.bind<Binding>(view)
}
