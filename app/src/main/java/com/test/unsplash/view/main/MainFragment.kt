package com.test.unsplash.view.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.unsplash.R
import com.test.unsplash.databinding.FragmentMainBinding
import com.test.unsplash.model.local.Collection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy {
        CollectionsAdapter(::handleItemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.rvCollections.adapter = adapter

        viewModel.collectionsLiveData.observe(viewLifecycleOwner, ::handleStateUpdate)
        viewModel.loadCollections()
    }

    private fun handleItemClick(collection: Collection) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToCollectionDetailsFragment(collection))
    }

    private fun handleStateUpdate(state: MainViewModel.State) {
        when (state) {
            is MainViewModel.State.Success -> onSuccessState(state.collections)
            is MainViewModel.State.Error -> onErrorState(state.exception)
            MainViewModel.State.Loading -> onLoadingState()
        }
    }

    private fun onSuccessState(data: List<Collection>) {
        adapter.submitList(data)
        binding.run {
            tvError.isVisible = false
            progressIndicator.isVisible = false
        }
    }

    private fun onErrorState(exception: Throwable?) {
        val errorMessage = exception?.localizedMessage ?: getString(R.string.fragment_main_default_error)
        if (adapter.currentList.isEmpty()) {
            binding.run {
                tvError.text = errorMessage
                tvError.isVisible = true
                progressIndicator.isVisible = false
            }
        } else {
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun onLoadingState() {
        if (adapter.currentList.isNotEmpty()) return

        binding.run {
            tvError.isVisible = false
            progressIndicator.isVisible = true
        }
    }

}