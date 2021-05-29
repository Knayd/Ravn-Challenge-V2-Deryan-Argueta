package com.android.ravn.dargueta.ui.list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseViewHolder
import com.android.ravn.dargueta.databinding.ItemLoadingFooterBinding
import com.android.ravn.dargueta.util.inflate
import com.android.ravn.dargueta.util.setLoadingState

class PersonLoadStateAdapter :
    LoadStateAdapter<PersonLoadStateAdapter.PersonLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PersonLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PersonLoadStateViewHolder(parent.inflate(R.layout.item_loading_footer))

    inner class PersonLoadStateViewHolder(view: View) :
        BaseViewHolder<ItemLoadingFooterBinding>(view) {

        fun bind(loadState: LoadState) {
            binding?.setLoadingState(loadState)
        }
    }

}