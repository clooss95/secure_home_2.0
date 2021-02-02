package com.bonacode.securehome.ui.feature.main.controlpanel.partition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.databinding.FragmentControlPanelPartitionFunctionsBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlPanelPartitionFunctionsFragment : ControlPanelSectionFragment<ControlPanelPartitionFunctionsViewModel, FragmentControlPanelPartitionFunctionsBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentControlPanelPartitionFunctionsBinding = FragmentControlPanelPartitionFunctionsBinding.inflate(inflater, container, false)

    override val viewModel: ControlPanelPartitionFunctionsViewModel by viewModels()

    companion object {
        fun newInstance(addToFavMode: Boolean) = ControlPanelPartitionFunctionsFragment().apply {
            arguments = Bundle().apply { putBoolean(ARG_IS_IN_ADD_TO_FAVOURITE_MODE, addToFavMode) }
        }
    }
}
