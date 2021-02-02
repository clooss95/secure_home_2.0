package com.bonacode.securehome.ui.feature.main.controlpanel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bonacode.securehome.R
import com.bonacode.securehome.application.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentControlPanelMainBinding
import com.bonacode.securehome.ui.feature.main.controlpanel.entry.ControlPanelEntryFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.line.ControlPanelLineFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.mainfunctions.ControlPanelMainFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.partition.ControlPanelPartitionFunctionsFragment
import com.bonacode.securehome.ui.feature.main.controlpanel.system.ControlPanelSystemFunctionsFragment
import com.bonacode.securehome.ui.views.ExpandableControlPanelView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlPanelMainFragment :
    ViewModelFragment<ControlPanelMainViewModel, FragmentControlPanelMainBinding>() {

    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentControlPanelMainBinding =
        FragmentControlPanelMainBinding.inflate(inflater, container, false)

    override val viewModel: ControlPanelMainViewModel by viewModels()

    override fun initViews() {
        safeGetBinding().mainFunctionsExpandableControlPanel.setupHeaderClickCallback()
        safeGetBinding().partitionFunctionsExpandableControlPanel.setupHeaderClickCallback()
        safeGetBinding().lineFunctionsExpandableControlPanel.setupHeaderClickCallback()
        safeGetBinding().entryFunctionsExpandableControlPanel.setupHeaderClickCallback()
        safeGetBinding().systemFunctionsExpandableControlPanel.setupHeaderClickCallback()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelMainFunctionsFragmentContainer,
            ControlPanelMainFunctionsFragment.newInstance(addToFavMode = false),
            "controlPanelMainFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelPartitionFunctionsFragmentContainer,
            ControlPanelPartitionFunctionsFragment.newInstance(addToFavMode = false),
            "controlPanelPartitionFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelLineFunctionsFragmentContainer,
            ControlPanelLineFunctionsFragment.newInstance(addToFavMode = false),
            "controlPanelLineFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelEntryFunctionsFragmentContainer,
            ControlPanelEntryFunctionsFragment.newInstance(addToFavMode = false),
            "controlPanelEntryFunctionsFragment"
        ).commit()

        childFragmentManager.beginTransaction().add(
            R.id.controlPanelSystemFunctionsFragmentContainer,
            ControlPanelSystemFunctionsFragment.newInstance(addToFavMode = false),
            "controlPanelSystemFunctionsFragment"
        ).commit()
    }

    override fun subscribe() {
        viewModel.sectionExpandedEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { expandedSection ->
                listOf(
                    safeGetBinding().mainFunctionsExpandableControlPanel,
                    safeGetBinding().partitionFunctionsExpandableControlPanel,
                    safeGetBinding().lineFunctionsExpandableControlPanel,
                    safeGetBinding().entryFunctionsExpandableControlPanel,
                    safeGetBinding().systemFunctionsExpandableControlPanel
                ).forEach { section ->
                    if (section != expandedSection) {
                        section.collapse()
                    }
                }
            }
        }
    }

    override fun unsubscribe() {
        viewModel.sectionExpandedEvent.removeObservers(this)
    }

    private fun ExpandableControlPanelView.setupHeaderClickCallback() {
        headerClickCallback = {
            viewModel.onSectionExpanded(this)
        }
    }
}
