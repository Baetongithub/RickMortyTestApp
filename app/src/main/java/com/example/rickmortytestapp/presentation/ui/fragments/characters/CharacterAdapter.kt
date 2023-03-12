package com.example.rickmortytestapp.presentation.ui.fragments.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickmortytestapp.databinding.ItemCharacterBinding
import com.example.rickmortytestapp.domain.model.character.ResultCharacter

class CharacterAdapter(private val onItemClick: (resultCharacter: ResultCharacter) -> Unit) :
    PagingDataAdapter<ResultCharacter, CharacterAdapter.RickMortyViewHolder>(UsersDiffCallback()) {

    override fun onBindViewHolder(holder: RickMortyViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.itemView.setOnClickListener { onItemClick(getItem(position)!!) }
            holder.bind(getItem(position)!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyViewHolder {
        return RickMortyViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class RickMortyViewHolder(private val vb: ItemCharacterBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(resultCharacter: ResultCharacter) {
            vb.imageIc.load(resultCharacter.image)
            vb.tvName.text = resultCharacter.name
            vb.tvStatus.text = resultCharacter.status
            vb.tvGender.text = resultCharacter.gender
        }
    }

    private class UsersDiffCallback : DiffUtil.ItemCallback<ResultCharacter>() {
        override fun areItemsTheSame(oldItem: ResultCharacter, newItem: ResultCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResultCharacter,
            newItem: ResultCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }
}
