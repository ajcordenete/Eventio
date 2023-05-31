package com.ajcordenete.eventio.home

import android.os.Bundle
import android.view.View
import com.ajcordenete.eventio.R
import com.ajcordenete.core.R as commonR
import com.ajcordenete.eventio.databinding.FragmentHomeBinding
import com.ajcordenete.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class FragmentHome: BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(commonR.string.home)
        )
    }
}