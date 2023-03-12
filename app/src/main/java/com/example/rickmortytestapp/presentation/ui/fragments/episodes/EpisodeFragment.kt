package com.example.rickmortytestapp.presentation.ui.fragments.episodes

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickmortytestapp.R
import com.example.rickmortytestapp.databinding.FragmentEpisodeBinding
import com.example.rickmortytestapp.databinding.ItemDialogSearchBinding
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode
import com.example.rickmortytestapp.presentation.ui.base.BaseFragment
import com.example.rickmortytestapp.presentation.ui.fragments.load_state.MyLoadStateAdapter
import com.example.rickmortytestapp.presentation.utils.ext.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(FragmentEpisodeBinding::inflate) {

    private val viewModel: EpisodesViewModel by viewModel()
    private val episodesAdapter = EpisodesAdapter(this::onItemClick)

    private val loadStateAdapter = MyLoadStateAdapter { episodesAdapter.retry() }
    private val gridLayoutManager = GridLayoutManager(context, 2)

    override fun initView() {
        super.initView()
        setupRecyclerView()
    }

    override fun initViewModel() {
        super.initViewModel()

        observeGetDefaultEpisodes()
        searchEpisodeInDialog()
    }

    private fun searchEpisodeInDialog() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_search, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.search_by_name -> {
                        showSearchDialog()
                        true
                    }
                    else -> false
                }
            }

            private fun showSearchDialog() {
                val dialogVb = activity?.layoutInflater?.let { ItemDialogSearchBinding.inflate(it) }

                val dialog = context?.let {
                    AlertDialog.Builder(it)
                        .setView(dialogVb?.root)
                        .setPositiveButton(getString(R.string.search)) { _, _ ->
                            observeSearchEpisode(dialogVb?.etSearchByName?.text.toString())
                        }
                }
                dialog?.create()?.show()
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observeGetDefaultEpisodes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getEpisodes.collectLatest { pagingData ->
                episodesAdapter.submitData(pagingData)
                pagingData.map { toast(it.air_date) }
            }
        }
    }

    private fun observeSearchEpisode(name: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchEpisode(name).collect { pagingData ->
                episodesAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecyclerView() = with(vb) {
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = episodesAdapter.withLoadStateFooter(footer = loadStateAdapter)
        }
        centralizeRetryButton()
    }

    private fun onItemClick(resultEpisode: ResultEpisode) {
        toast(resultEpisode.id.toString())
    }

    private fun centralizeRetryButton() {
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == episodesAdapter.itemCount && loadStateAdapter.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }
    }
}