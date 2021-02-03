package com.bonacode.securehome.ui.feature.main.actionHistory

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.bonacode.securehome.R
import com.bonacode.securehome.application.common.SwipeToDeleteCallback
import com.bonacode.securehome.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentActionHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class ActionHistoryFragment :
    ViewModelFragment<ActionHistoryViewModel, FragmentActionHistoryBinding>() {

    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentActionHistoryBinding =
        FragmentActionHistoryBinding.inflate(inflater, container, false)

    override val viewModel: ActionHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_action_history, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.selectDate -> openSelectDateDialog()
        }

        return false
    }

    override fun subscribe() {
        viewModel.actionHistoryList.observe(this) {
            lifecycleScope.launch {
                it?.let { data ->
                    (safeGetBinding().actionHistoryList.adapter as ActionHistoryAdapter).submitData(
                        data
                    )
                }
            }
        }
        viewModel.showDialogQuestionClearHistoryEvent.observe(this) {
            it?.getContentIfNotHandled()?.let {
                MaterialDialog(requireContext()).show {
                    title(text = getString(R.string.dialog_clear_history_title))
                    positiveButton(R.string.yes) {
                        viewModel.clearHistory()
                    }
                    negativeButton(R.string.cancel)
                }
            }
        }
    }

    override fun unsubscribe() {
        viewModel.actionHistoryList.removeObservers(this)
        viewModel.showDialogQuestionClearHistoryEvent.removeObservers(this)
    }

    override fun initViews() {
        safeGetBinding().actionHistoryList.adapter = ActionHistoryAdapter {
            viewModel.retryClicked(it)
        }

        (safeGetBinding().actionHistoryList.adapter as? ActionHistoryAdapter)?.let { adapter ->
            adapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                    safeGetBinding().actionHistoryList.isVisible = false
                    safeGetBinding().emptyListView.isVisible = true
                } else {
                    safeGetBinding().actionHistoryList.isVisible = true
                    safeGetBinding().emptyListView.isVisible = false
                }
            }
        }

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as ActionHistoryAdapter.ActionHistoryViewHolder).getModel()?.let {
                    viewModel.deleteActionHistory(it)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(safeGetBinding().actionHistoryList)

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        safeGetBinding().actionHistoryList.addItemDecoration(dividerItemDecoration)
    }

    private fun openSelectDateDialog() {
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                viewModel.dateSelected(
                    year,
                    monthOfYear,
                    dayOfMonth
                )
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        dialog.show()
    }
}
