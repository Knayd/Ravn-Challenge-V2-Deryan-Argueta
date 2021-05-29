package com.android.ravn.dargueta.ui.list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseViewHolder
import com.android.ravn.dargueta.databinding.ItemPersonBinding
import com.android.ravn.dargueta.util.inflate
import com.android.ravn.data.util.ResourceManager
import com.android.ravn.domain.model.Person

class PersonAdapter(
    private val resourceManager: ResourceManager,
    val onPersonClick: (person: Person) -> Unit
) :
    PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(parent.inflate(R.layout.item_person))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { currentPerson ->
            holder.binding?.apply {
                person = currentPerson
                root.setOnClickListener { onPersonClick.invoke(currentPerson) }

                val species =
                    currentPerson.species ?: resourceManager.getString(R.string.unknown_species)

                val planet =
                    currentPerson.homeWorld ?: resourceManager.getString(R.string.unknown_planet)

                tvDescription.text =
                    resourceManager.getString(R.string.person_description, species, planet)
            }
        }
    }

    inner class PersonViewHolder(view: View) : BaseViewHolder<ItemPersonBinding>(view)
}