package com.android.ravn.dargueta.ui.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseFragment
import com.android.ravn.dargueta.databinding.FragmentPeopleListBinding
import com.android.ravn.dargueta.ui.list.adapter.PersonAdapter
import com.android.ravn.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : BaseFragment<FragmentPeopleListBinding>(
    R.layout.fragment_people_list
) {

    override val viewModel: PeopleListViewModel by viewModels()

    private val adapter: PersonAdapter by lazy {
        PersonAdapter()
    }

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        binding.rvPeople.adapter = adapter
        viewModel.people.observe(viewLifecycleOwner, getPeopleObserver())
    }

    private fun getPeopleObserver() = Observer<List<Person>> { people ->
        people?.let {
            adapter.submitList(people)
        }
    }
}