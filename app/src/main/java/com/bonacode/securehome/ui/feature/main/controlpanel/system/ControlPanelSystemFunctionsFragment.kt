package com.bonacode.securehome.ui.feature.main.controlpanel.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.databinding.FragmentControlPanelSystemFunctionsBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlPanelSystemFunctionsFragment :
    ControlPanelSectionFragment<ControlPanelSystemFunctionsViewModel, FragmentControlPanelSystemFunctionsBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentControlPanelSystemFunctionsBinding =
        FragmentControlPanelSystemFunctionsBinding.inflate(inflater, container, false)

    override val viewModel: ControlPanelSystemFunctionsViewModel by viewModels()

    companion object {
        fun newInstance(addToFavMode: Boolean) = ControlPanelSystemFunctionsFragment().apply {
            arguments = Bundle().apply { putBoolean(ARG_IS_IN_ADD_TO_FAVOURITE_MODE, addToFavMode) }
        }
    }
}
