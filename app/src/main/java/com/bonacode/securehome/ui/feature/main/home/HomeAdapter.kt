package com.bonacode.securehome.ui.feature.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bonacode.securehome.databinding.ItemDefaultActionBinding
import com.bonacode.securehome.databinding.ItemFavouriteActionBinding
import com.bonacode.securehome.domain.feature.home.model.HomeItemModel

class HomeAdapter(
    private val itemClickedCallback: (HomeItemModel) -> Unit
) :
    ListAdapter<HomeItemModel, RecyclerView.ViewHolder>(
        REPO_COMPARATOR
    ) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<HomeItemModel>() {
            override fun areItemsTheSame(
                oldItem: HomeItemModel,
                newItem: HomeItemModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: HomeItemModel,
                newItem: HomeItemModel
            ) = oldItem == newItem
        }

        private const val TYPE_DEFAULT = 1
        private const val TYPE_FAVOURITE = 2
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is HomeItemModel.Default -> TYPE_DEFAULT
        is HomeItemModel.Favourite -> TYPE_FAVOURITE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_DEFAULT -> DefaultActionViewHolder(
                ItemDefaultActionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_FAVOURITE -> FavouriteActionViewHolder(
                ItemFavouriteActionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalStateException("Unknown viewType: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DefaultActionViewHolder -> holder.bind(getItem(position) as HomeItemModel.Default)
            is FavouriteActionViewHolder -> holder.bind(getItem(position) as HomeItemModel.Favourite)
        }
    }

    inner class DefaultActionViewHolder(private val binding: ItemDefaultActionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeItemModel.Default?) {
            binding.model = model
            binding.rootView.setOnClickListener {
                model?.let {
                    itemClickedCallback(it)
                }
            }
            binding.executePendingBindings()
        }
    }

    inner class FavouriteActionViewHolder(private val binding: ItemFavouriteActionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeItemModel.Favourite?) {
            binding.model = model
            binding.rootView.setOnClickListener {
                model?.let {
                    itemClickedCallback(it)
                }
            }
            binding.executePendingBindings()
        }

        fun getFavouriteActionId(): Long? = binding.model?.id
    }
}
