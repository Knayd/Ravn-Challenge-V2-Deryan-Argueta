package com.android.ravn.dargueta.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.android.ravn.dargueta.BR

abstract class BaseFragment<Binding : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    /**
     * Binding reference needs to be cleared on [onDestroyView] in order to avoid
     * a memory leak.
     */
    private var _binding: Binding? = null
    abstract val viewModel: ViewModel?

    /**
     * This variable should only be accessed between [onCreateView] and [onDestroyView].
     *
     * Throws an [IllegalArgumentException] if [_binding] is null.
     */
    val binding: Binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        ) as Binding

        return _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel?.let { setVariable(BR.viewModel, it) }
        }?.root ?: View(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentReady(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Called when this [Fragment] is ready.
     */
    abstract fun onFragmentReady(savedInstanceState: Bundle?)
}