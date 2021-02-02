package com.bonacode.securehome.ui.feature.main.home.addtofavourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.R
import com.bonacode.securehome.application.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentAddToFavouriteBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.entry.ControlPanelEntryFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.line.ControlPanelLineFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.mainfunctions.ControlPanelMainFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.partition.ControlPanelPartitionFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.system.ControlPanelSystemFunctionsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToFavouriteFragment : ViewModelFragment<AddToFavouriteViewModel, FragmentAddToFavouriteBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddToFavouriteBinding = FragmentAddToFavouriteBinding.inflate(inflater, container, false)

    override val viewModel: AddToFavouriteViewModel by viewModels()

    override fun initViews() {
        childFragmentManager.beginTransaction().add(
            R.id.controlPanelMainFunctionsFragmentContainer,
            ControlPanelMainFunctionsFragment.newInstance(addToFavMode = true),
            "controlPanelMainFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelPartitionFunctionsFragmentContainer,
            ControlPanelPartitionFunctionsFragment.newInstance(addToFavMode = true),
            "controlPanelPartitionFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelLineFunctionsFragmentContainer,
            ControlPanelLineFunctionsFragment.newInstance(addToFavMode = true),
            "controlPanelLineFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelEntryFunctionsFragmentContainer,
            ControlPanelEntryFunctionsFragment.newInstance(addToFavMode = true),
            "controlPanelEntryFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelSystemFunctionsFragmentContainer,
            ControlPanelSystemFunctionsFragment.newInstance(addToFavMode = true),
            "controlPanelSystemFunctionsFragment"
        ).commit()
    }
}
