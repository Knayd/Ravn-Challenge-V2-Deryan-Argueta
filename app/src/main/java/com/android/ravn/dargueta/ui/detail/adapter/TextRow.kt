package com.android.ravn.dargueta.ui.detail.adapter

sealed class TextRow {
    data class Title(val text: String) : TextRow()
    data class Content(
        val text1: String? = null,
        val text2: String? = null
    ) : TextRow()
}
