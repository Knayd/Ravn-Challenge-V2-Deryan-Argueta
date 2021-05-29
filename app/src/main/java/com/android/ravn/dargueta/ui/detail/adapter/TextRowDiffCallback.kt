package com.android.ravn.dargueta.ui.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.ravn.dargueta.ui.textrow.TextRow

class TextRowDiffCallback : DiffUtil.ItemCallback<TextRow>() {
    override fun areItemsTheSame(oldItem: TextRow, newItem: TextRow) =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: TextRow, newItem: TextRow) =
        oldItem == newItem
}