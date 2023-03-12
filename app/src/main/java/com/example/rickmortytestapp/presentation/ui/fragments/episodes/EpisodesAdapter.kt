package com.example.rickmortytestapp.presentation.ui.fragments.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytestapp.databinding.ItemLocationEpisodeBinding
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode

class EpisodesAdapter(private val onItemClick: (resultEpisode: ResultEpisode) -> Unit) :
    PagingDataAdapter<ResultEpisode, EpisodesAdapter.EpisodesViewHolder>(UsersDiffCallback()) {

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.itemView.setOnClickListener { getItem(position)?.let { it1 -> onItemClick(it1) } }
            holder.bind(getItem(position)!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemLocationEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class EpisodesViewHolder(private val vb: ItemLocationEpisodeBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(resultEpisode: ResultEpisode) {
            vb.tvName.text = resultEpisode.name
            vb.tvDescription.text = resultEpisode.air_date
        }
    }

    private class UsersDiffCallback : DiffUtil.ItemCallback<ResultEpisode>() {
        override fun areItemsTheSame(oldItem: ResultEpisode, newItem: ResultEpisode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultEpisode, newItem: ResultEpisode): Boolean {
            return oldItem == newItem
        }
    }
}
