package com.android.ravn.dargueta.ui.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseFragment
import com.android.ravn.dargueta.databinding.FragmentPeopleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : BaseFragment<FragmentPeopleListBinding>(
    R.layout.fragment_people_list
) {

    override val viewModel: PeopleListViewModel by viewModels()

    override fun onFragmentReady(savedInstanceState: Bundle?) {

    }
}