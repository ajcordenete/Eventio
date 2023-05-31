package com.ajcordenete.eventio.feature.home

import android.os.Bundle
import android.view.View
import com.ajcordenete.eventio.R
import com.ajcordenete.core.R as commonR
import com.ajcordenete.eventio.databinding.FragmentHomeBinding
import com.ajcordenete.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajcordenete.core.ext.navigate
import com.ajcordenete.core.ext.ninjaTap
import com.ajcordenete.eventio.feature.list.adapter.EventsAdapter
import com.ajcordenete.eventio.utils.ViewUtils
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentHome: BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private val eventAdapter by lazy {
        EventsAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

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
            getString(commonR.string.home)
        )
    }

    private fun setUpViews() {
        binding
            .imgDashboard
            .ninjaTap {
                navigate(
                    FragmentHomeDirections
                        .actionFragmentHomeToFragmentAction()
                )
            }
            .launchIn(lifecycleScope)

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

    private fun handleState(state: HomeUIState) {
        when(state) {
            is HomeUIState.ShowEvents -> {
                eventAdapter.updateItems(state.events, true)
            }
            is HomeUIState.ShowEventsCount -> {
                binding.txtEventCount.text = getString(
                    R.string.total_events,
                    state.count
                )
            }
            is HomeUIState.ShowLoading -> {

            }
            is HomeUIState.ShowCacheLoading -> {

            }
            is HomeUIState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(
                    binding.root,
                    state.errorMessage
                )
            }
        }
    }
}