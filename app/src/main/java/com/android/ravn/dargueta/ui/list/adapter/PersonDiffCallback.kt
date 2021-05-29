package com.android.ravn.dargueta.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.ravn.domain.model.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person) =
        oldItem == newItem
}
