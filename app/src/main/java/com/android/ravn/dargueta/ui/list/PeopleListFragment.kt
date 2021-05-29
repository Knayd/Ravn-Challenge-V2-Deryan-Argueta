package com.android.ravn.dargueta.ui.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseFragment
import com.android.ravn.dargueta.databinding.FragmentPeopleListBinding
import com.android.ravn.dargueta.ui.list.adapter.PersonAdapter
import com.android.ravn.dargueta.ui.list.adapter.PersonLoadStateAdapter
import com.android.ravn.dargueta.util.setLoadingState
import com.android.ravn.data.util.ResourceManager
import com.android.ravn.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeopleListFragment : BaseFragment<FragmentPeopleListBinding>(
    R.layout.fragment_people_list
) {

    override val viewModel: PeopleListViewModel by viewModels()

    @Inject
    lateinit var resourceManager: ResourceManager

    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(
            resourceManager = resourceManager,
            onPersonClick = { person -> openDetails(person) })
    }

    private val stateAdapter: PersonLoadStateAdapter by lazy {
        PersonLoadStateAdapter()
    }

    private fun openDetails(person: Person) {
        findNavController().navigate(
            PeopleListFragmentDirections.toPersonDetailFragment(person, person.name)
        )
    }

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        binding.rvPeople.adapter = personAdapter.apply {
            withLoadStateFooter(footer = stateAdapter)
            addLoadStateListener { loadState ->
                binding.layoutLoading.setLoadingState(loadState.source.refresh)
            }
        }
        viewModel.people.observe(viewLifecycleOwner, getPeopleObserver())
    }

    private fun getPeopleObserver() = Observer<PagingData<Person>> { data ->
        data?.let {
            personAdapter.submitData(lifecycle, data)
        }
    }
}