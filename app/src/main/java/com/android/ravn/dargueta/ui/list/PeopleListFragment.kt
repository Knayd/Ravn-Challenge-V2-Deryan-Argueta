package com.android.ravn.dargueta.ui.list

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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
            onPersonClick = { person -> openDetails(person) }
        )
    }

    /**
     * Helper adapter to display either a loading or error message when doing pagination.
     * */

    private val loadStateAdapter: PersonLoadStateAdapter by lazy {
        PersonLoadStateAdapter()
    }

    private fun openDetails(person: Person) {
        findNavController().navigate(
            PeopleListFragmentDirections.toPersonDetailFragment(person, person.name)
        )
    }

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        viewModel.people.observe(viewLifecycleOwner, getPeopleObserver())
        binding.rvPeople.adapter = personAdapter.withLoadStateFooter(footer = loadStateAdapter)
        personAdapter.addLoadStateListener { loadState ->
            // Setting listener in order to display loading/error message
            // or an empty state when there are no results
            binding.layoutLoading.setLoadingState(loadState.refresh)
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && personAdapter.itemCount == EMPTY_RESULT
            binding.tvEmptyState.isVisible = isListEmpty
        }
    }

    private fun getPeopleObserver() = Observer<PagingData<Person>> { data ->
        data?.let {
            personAdapter.submitData(lifecycle, data)
        }
    }

    companion object {
        private const val EMPTY_RESULT = 0
    }
}
