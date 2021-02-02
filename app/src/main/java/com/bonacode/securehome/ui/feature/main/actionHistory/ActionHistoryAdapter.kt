package com.bonacode.securehome.ui.feature.main.actionHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bonacode.securehome.databinding.ItemActionHistoryBinding
import com.bonacode.securehome.domain.feature.actionhistory.model.ActionHistoryModel

class ActionHistoryAdapter(
    private val itemClickedCallback: (ActionHistoryModel) -> Unit
) :
    PagingDataAdapter<ActionHistoryModel, ActionHistoryAdapter.ActionHistoryViewHolder>(
        REPO_COMPARATOR
    ) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ActionHistoryModel>() {
            override fun areItemsTheSame(
                oldItem: ActionHistoryModel,
                newItem: ActionHistoryModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ActionHistoryModel,
                newItem: ActionHistoryModel
            ) =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionHistoryViewHolder =
        ActionHistoryViewHolder(
            ItemActionHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ActionHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ActionHistoryViewHolder(private val binding: ItemActionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ActionHistoryModel?) {
            binding.model = model
            binding.retryClickListener = View.OnClickListener {
                model?.let {
                    itemClickedCallback(it)
                }
            }
            binding.executePendingBindings()
        }

        fun getModel(): ActionHistoryModel? = binding.model
    }
}
