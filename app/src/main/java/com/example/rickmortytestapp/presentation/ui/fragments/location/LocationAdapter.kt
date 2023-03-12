package com.example.rickmortytestapp.presentation.ui.fragments.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytestapp.databinding.ItemLocationEpisodeBinding
import com.example.rickmortytestapp.domain.model.location.ResultLocation

class LocationAdapter(private val onItemClick: (resultLocation: ResultLocation) -> Unit) :
    PagingDataAdapter<ResultLocation, LocationAdapter.LocationViewHolder>(UsersDiffCallback()) {

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.itemView.setOnClickListener { getItem(position)?.let { it1 -> onItemClick(it1) } }
            getItem(position)?.let { holder.bind(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class LocationViewHolder(private val vb: ItemLocationEpisodeBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(result: ResultLocation) {
            vb.tvName.text = result.name
            vb.tvDescription.text = result.dimension
        }
    }

    private class UsersDiffCallback : DiffUtil.ItemCallback<ResultLocation>() {
        override fun areItemsTheSame(oldItem: ResultLocation, newItem: ResultLocation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultLocation, newItem: ResultLocation): Boolean {
            return oldItem == newItem
        }
    }
}
