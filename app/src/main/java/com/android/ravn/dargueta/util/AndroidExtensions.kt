package com.android.ravn.dargueta.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes viewLayout: Int): View =
    LayoutInflater.from(this.context).inflate(viewLayout, this, false)