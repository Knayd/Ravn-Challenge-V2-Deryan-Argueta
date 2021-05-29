package com.android.ravn.dargueta.ui.list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseViewHolder
import com.android.ravn.dargueta.databinding.ItemPersonBinding
import com.android.ravn.dargueta.util.inflate
import com.android.ravn.domain.model.Person

class PersonAdapter :
    PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(parent.inflate(R.layout.item_person))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding?.person = getItem(position)
    }

    inner class PersonViewHolder(view: View) : BaseViewHolder<ItemPersonBinding>(view)
}