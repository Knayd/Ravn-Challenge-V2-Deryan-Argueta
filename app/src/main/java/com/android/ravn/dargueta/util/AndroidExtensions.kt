package com.android.ravn.dargueta.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.android.ravn.dargueta.databinding.ItemLoadingFooterBinding

fun ViewGroup.inflate(@LayoutRes viewLayout: Int): View =
    LayoutInflater.from(this.context).inflate(viewLayout, this, false)

fun TextView.visibleIfNotNullNorEmpty(text: String?) {
    if (text.isNullOrBlank()) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        this.text = text
    }
}

fun ItemLoadingFooterBinding.setLoadingState(loadState: LoadState) {
    val isError = loadState is LoadState.Error
    val isLoading = loadState is LoadState.Loading
    tvErrorLabel.isVisible = isError
    tvLoadingLabel.isVisible = isLoading
    piLoading.isVisible = isLoading
}