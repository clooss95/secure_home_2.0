package com.bonacode.securehome.ui.feature.main.controlpanel.line

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.databinding.FragmentControlPanelLineFunctionsBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlPanelLineFunctionsFragment : ControlPanelSectionFragment<ControlPanelLineFunctionsViewModel, FragmentControlPanelLineFunctionsBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentControlPanelLineFunctionsBinding = FragmentControlPanelLineFunctionsBinding.inflate(inflater, container, false)

    override val viewModel: ControlPanelLineFunctionsViewModel by viewModels()

    companion object {
        fun newInstance(addToFavMode: Boolean) = ControlPanelLineFunctionsFragment().apply {
            arguments = Bundle().apply { putBoolean(ARG_IS_IN_ADD_TO_FAVOURITE_MODE, addToFavMode) }
        }
    }
}
