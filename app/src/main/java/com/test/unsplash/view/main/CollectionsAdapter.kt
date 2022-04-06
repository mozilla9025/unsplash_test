package com.test.unsplash.view.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.unsplash.databinding.ItemCollectionBinding
import com.test.unsplash.model.local.Collection

class CollectionsAdapter(
    private inline val doOnClick: (collection: Collection) -> Unit
) : ListAdapter<Collection, CollectionsAdapter.ViewHolder>(CollectionDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCollectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    inner class ViewHolder(private val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Collection) {
            binding.run {
                root.setOnClickListener { doOnClick(item) }
                ivCover.setBackgroundColor(Color.parseColor(item.coverPhoto.color))
                tvCollectionName.text = item.title
                Glide.with(ivCover)
                    .load(item.coverPhoto.urls.regular)
                    .into(ivCover)
            }
        }
    }

    private object CollectionDiffCallback : DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(
            oldItem: Collection,
            newItem: Collection
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Collection,
            newItem: Collection
        ) = oldItem == newItem

    }
}