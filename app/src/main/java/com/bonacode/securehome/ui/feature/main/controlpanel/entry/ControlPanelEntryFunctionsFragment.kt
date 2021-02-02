package com.bonacode.securehome.ui.feature.main.controlpanel.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.databinding.FragmentControlPanelEntryFunctionsBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlPanelEntryFunctionsFragment : ControlPanelSectionFragment<ControlPanelEntryFunctionsViewModel, FragmentControlPanelEntryFunctionsBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentControlPanelEntryFunctionsBinding = FragmentControlPanelEntryFunctionsBinding.inflate(inflater, container, false)

    override val viewModel: ControlPanelEntryFunctionsViewModel by viewModels()

    companion object {
        fun newInstance(addToFavMode: Boolean) = ControlPanelEntryFunctionsFragment().apply {
            arguments = Bundle().apply { putBoolean(ARG_IS_IN_ADD_TO_FAVOURITE_MODE, addToFavMode) }
        }
    }
}
