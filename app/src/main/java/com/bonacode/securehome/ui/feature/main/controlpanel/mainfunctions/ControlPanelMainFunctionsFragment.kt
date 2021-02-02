package com.bonacode.securehome.ui.feature.main.controlpanel.mainfunctions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.databinding.FragmentControlPanelMainFunctionsBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlPanelMainFunctionsFragment :
    ControlPanelSectionFragment<ControlPanelMainFunctionsViewModel, FragmentControlPanelMainFunctionsBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentControlPanelMainFunctionsBinding =
        FragmentControlPanelMainFunctionsBinding.inflate(inflater, container, false)

    override val viewModel: ControlPanelMainFunctionsViewModel by viewModels()

    companion object {
        fun newInstance(addToFavMode: Boolean) = ControlPanelMainFunctionsFragment().apply {
            arguments = Bundle().apply { putBoolean(ARG_IS_IN_ADD_TO_FAVOURITE_MODE, addToFavMode) }
        }
    }
}
