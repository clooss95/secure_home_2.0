package com.bonacode.securehome.ui.feature.main.controlpanel

import androidx.databinding.ViewDataBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.bonacode.securehome.R
import com.bonacode.securehome.application.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.ui.feature.common.ValuePickerDialog

abstract class ControlPanelSectionFragment<VM : ControlPanelSectionViewModel, B : ViewDataBinding>() :
    ViewModelFragment<VM, B>() {

    override fun subscribe() {
        viewModel.selectGroupEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { callback ->
                ValuePickerDialog.showForGroup(requireContext()) { group ->
                    callback(group)
                }
            }
        }
        viewModel.selectPartitionEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { callback ->
                ValuePickerDialog.showForPartition(requireContext()) { partition ->
                    callback(partition)
                }
            }
        }
        viewModel.selectLineEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { callback ->
                ValuePickerDialog.showForLine(requireContext()) { line ->
                    callback(line)
                }
            }
        }
        viewModel.selectEntryEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { callback ->
                ValuePickerDialog.showForEntry(requireContext()) { entry ->
                    callback(entry)
                }
            }
        }
        viewModel.askForActionNameAndSaveFavActionEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { callback ->
                MaterialDialog(requireContext()).show {
                    title(text = getString(R.string.dialog_select_fav_name_set_favourite_action_name))
                    input(
                        hint = getString(R.string.dialog_select_fav_name_enter_fav_action_name),
                        allowEmpty = false
                    ) { _, text -> callback(text.toString()) }
                    positiveButton(R.string.ok)
                }
            }
        }
    }

    override fun unsubscribe() {
        viewModel.selectGroupEvent.removeObservers(this)
        viewModel.selectPartitionEvent.removeObservers(this)
        viewModel.selectLineEvent.removeObservers(this)
        viewModel.selectEntryEvent.removeObservers(this)
        viewModel.askForActionNameAndSaveFavActionEvent.removeObservers(this)
    }

    companion object {
        const val ARG_IS_IN_ADD_TO_FAVOURITE_MODE = "arg_is_in_add_to_favourite_mode"
    }
}
