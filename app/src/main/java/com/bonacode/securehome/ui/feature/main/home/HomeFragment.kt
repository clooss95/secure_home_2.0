package com.bonacode.securehome.ui.feature.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bonacode.securehome.application.common.SwipeToDeleteCallback
import com.bonacode.securehome.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewModelFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by viewModels()

    override fun initViews() {
        safeGetBinding().favouriteActionList.adapter = HomeAdapter { action ->
            viewModel.sendAction(action)
        }

        val swipeHandler = object : SwipeToDeleteCallback(requireContext(), {
            it is HomeAdapter.FavouriteActionViewHolder
        }) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder is HomeAdapter.FavouriteActionViewHolder) {
                    viewHolder.getFavouriteActionId()?.let {
                        viewModel.disableFavouriteAction(it)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(safeGetBinding().favouriteActionList)

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        safeGetBinding().favouriteActionList.addItemDecoration(dividerItemDecoration)
    }

    override fun subscribe() {
        viewModel.homeActions.observe(this) {
            it?.let { list -> (safeGetBinding().favouriteActionList.adapter as? HomeAdapter)?.submitList(list) }
        }
    }

    override fun unsubscribe() {
        viewModel.homeActions.removeObservers(this)
    }
}
