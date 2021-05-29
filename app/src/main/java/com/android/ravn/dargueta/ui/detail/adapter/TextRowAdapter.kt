package com.android.ravn.dargueta.ui.detail.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseViewHolder
import com.android.ravn.dargueta.databinding.ItemTextRowContentBinding
import com.android.ravn.dargueta.databinding.ItemTextRowTitleBinding
import com.android.ravn.dargueta.util.inflate
import com.android.ravn.dargueta.util.visibleIfNotNullNorEmpty

class TextRowAdapter : ListAdapter<TextRow, RecyclerView.ViewHolder>(TextRowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == TYPE_TITLE) {
            TitleViewHolder(parent.inflate(R.layout.item_text_row_title))
        } else {
            ContentViewHolder(parent.inflate(R.layout.item_text_row_content))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as? TitleViewHolder)?.let {
            val currentItem = getItem(position) as TextRow.Title
            holder.binding?.tvTitle?.text = currentItem.text
        }

        (holder as? ContentViewHolder)?.let {
            val currentItem = getItem(position) as TextRow.Content
            holder.binding?.apply {
                tvRowText1.visibleIfNotNullNorEmpty(currentItem.text1)
                tvRowText2.visibleIfNotNullNorEmpty(currentItem.text2)
            }
        }
    }


    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is TextRow.Content -> TYPE_CONTENT
        is TextRow.Title -> TYPE_TITLE
    }

    companion object {
        private const val TYPE_TITLE = 1
        private const val TYPE_CONTENT = 2
    }

    inner class TitleViewHolder(view: View) : BaseViewHolder<ItemTextRowTitleBinding>(view)

    inner class ContentViewHolder(view: View) : BaseViewHolder<ItemTextRowContentBinding>(view)

}