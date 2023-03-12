package com.example.rickmortytestapp.presentation.ui.fragments.load_state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytestapp.R
import com.example.rickmortytestapp.databinding.ItemLoadStateBinding

class LoadStateViewHolder(
    private val itemBinding: ItemLoadStateBinding,
    private val context: Context,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    itemBinding.root
) {

    init {
        itemBinding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            itemBinding.tvLoadStateMessage.text = context.getString(R.string.no_internet)
        }
        itemBinding.progressBar.visibility = toVisibility(loadState is LoadState.Loading)
        itemBinding.btnRetry.visibility = toVisibility(loadState is LoadState.NotLoading)
        itemBinding.tvLoadStateMessage.visibility = toVisibility(loadState is LoadState.NotLoading)
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
            val vb = ItemLoadStateBinding.bind(view)
            return LoadStateViewHolder(vb, parent.context, retry)
        }
    }

    private fun toVisibility(constraint: Boolean): Int = if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}