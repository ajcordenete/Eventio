package com.ajcordenete.eventio.feature.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajcordenete.core.base.BaseFragment
import com.ajcordenete.eventio.R
import com.ajcordenete.eventio.databinding.FragmentListBinding
import com.ajcordenete.eventio.feature.list.adapter.EventsAdapter
import com.ajcordenete.eventio.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment: BaseFragment<FragmentListBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_list

    override fun canBack(): Boolean = true

    private val viewModel by viewModels<ListViewModel>()

    private val eventAdapter by lazy {
        EventsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpViews()
        setUpVmObserver()

        viewModel.getEvents()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(R.string.your_events)
        )
    }

    private fun setUpViews() {
        binding
            .listEvents
            .adapter = eventAdapter
    }

    private fun setUpVmObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .uiState
                .collect(::handleState)
        }
    }

    private fun handleState(state: ListUIState) {
        when(state) {
            is ListUIState.ShowEvents -> {
                eventAdapter.updateItems(state.events, true)
            }
            is ListUIState.ShowCacheLoading -> {

            }
            is ListUIState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(
                    binding.root,
                    state.errorMessage
                )
            }
        }
    }
}