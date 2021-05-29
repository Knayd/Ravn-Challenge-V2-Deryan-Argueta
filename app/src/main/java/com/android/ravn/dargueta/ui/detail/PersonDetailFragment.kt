package com.android.ravn.dargueta.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.base.BaseFragment
import com.android.ravn.dargueta.databinding.FragmentPersonDetailBinding
import com.android.ravn.dargueta.ui.detail.adapter.TextRowAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailFragment : BaseFragment<FragmentPersonDetailBinding>(
    R.layout.fragment_person_detail
) {
    override val viewModel: PersonDetailViewModel by viewModels()

    private val adapter: TextRowAdapter by lazy {
        TextRowAdapter()
    }

    private val args: PersonDetailFragmentArgs by navArgs()

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        val rows = viewModel.getTextRows(args.person)
        binding.rvRows.adapter = adapter
        adapter.submitList(rows)
    }
}