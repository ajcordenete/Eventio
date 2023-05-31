package com.ajcordenete.eventio.feature.action

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajcordenete.core.base.BaseViewModelFragment
import com.ajcordenete.core.ext.ninjaTap
import com.ajcordenete.eventio.R
import com.ajcordenete.eventio.databinding.FragmentActionBinding
import com.ajcordenete.eventio.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActionFragment: BaseViewModelFragment<FragmentActionBinding, ActionViewModel>() {

    private val viewModel by viewModels<ActionViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_action

    override fun canBack(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpViews()
        setUpVmObserver()
    }

    private fun setUpViews() {
        binding
            .button1
            .ninjaTap {
                viewModel.recordEvent(getButtonName(it as Button))
            }
            .launchIn(lifecycleScope)

        binding
            .button2
            .ninjaTap {
                viewModel.recordEvent(getButtonName(it as Button))
            }
            .launchIn(lifecycleScope)

        binding
            .button3
            .ninjaTap {
                viewModel.recordEvent(getButtonName(it as Button))
            }
            .launchIn(lifecycleScope)

        binding
            .button4
            .ninjaTap {
                viewModel.recordEvent(getButtonName(it as Button))
            }
            .launchIn(lifecycleScope)
    }

    private fun setUpVmObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .uiState
                .collect(::handleState)
        }
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(R.string.select_an_event)
        )
    }

    private fun handleState(state: ActionUIState) {
        when(state) {
            is ActionUIState.EventSaved -> {
                ViewUtils.showGenericSuccessSnackBar(
                    binding.root,
                    getString(R.string.event_saved)
                )
            }
            is ActionUIState.ShowLoading -> {

            }
            is ActionUIState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(
                    binding.root,
                    state.errorMessage
                )
            }
        }
    }

    private fun getButtonName(button: Button): String {
        return button.text.toString()
    }
}