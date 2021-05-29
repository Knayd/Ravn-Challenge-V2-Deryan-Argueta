package com.android.ravn.dargueta.ui.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseFragment
import com.android.ravn.dargueta.databinding.FragmentPeopleListBinding
import com.android.ravn.dargueta.ui.list.adapter.PersonAdapter
import com.android.ravn.dargueta.ui.list.adapter.PersonLoadStateAdapter
import com.android.ravn.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : BaseFragment<FragmentPeopleListBinding>(
    R.layout.fragment_people_list
) {

    override val viewModel: PeopleListViewModel by viewModels()

    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter()
    }

    private val stateAdapter: PersonLoadStateAdapter by lazy {
        PersonLoadStateAdapter()
    }

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        binding.rvPeople.adapter = personAdapter.withLoadStateFooter(
            footer = stateAdapter
        )
        viewModel.people.observe(viewLifecycleOwner, getPeopleObserver())
    }

    private fun getPeopleObserver() = Observer<PagingData<Person>> { data ->
        data?.let {
            personAdapter.submitData(lifecycle, data)
        }
    }
}