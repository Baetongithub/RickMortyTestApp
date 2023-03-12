package com.example.rickmortytestapp.presentation.ui.fragments.location

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickmortytestapp.R
import com.example.rickmortytestapp.databinding.FragmentLocationBinding
import com.example.rickmortytestapp.databinding.ItemDialogSearchBinding
import com.example.rickmortytestapp.domain.model.location.ResultLocation
import com.example.rickmortytestapp.presentation.ui.base.BaseFragment
import com.example.rickmortytestapp.presentation.ui.fragments.load_state.MyLoadStateAdapter
import com.example.rickmortytestapp.presentation.utils.ext.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalPagingApi
class LocationsFragment : BaseFragment<FragmentLocationBinding>(FragmentLocationBinding::inflate) {

    private val viewModel: LocationViewModel by viewModel()
    private val locationAdapter = LocationAdapter(this::onItemClick)

    private val gridLayoutManager = GridLayoutManager(context, 2)
    private val loadStateAdapter = MyLoadStateAdapter { locationAdapter.retry() }

    override fun initView() {
        super.initView()
        setupRecyclerView()
    }

    override fun initViewModel() {
        super.initViewModel()

        observeGetDefaultLocations()
        searchLocationInDialog()
    }

    private fun searchLocationInDialog() {
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
                val searchBinding = activity?.layoutInflater?.let { ItemDialogSearchBinding.inflate(it) }

                val dialog = context?.let {
                    AlertDialog.Builder(it)
                        .setView(searchBinding?.root)
                        .setPositiveButton(getString(R.string.search)) { _, _ ->
                            observeSearchLocation(searchBinding?.etSearchByName?.text.toString())
                        }
                }

                if (dialog != null) {
                    dialog.create()
                    dialog.show()
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observeGetDefaultLocations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getLocations.collectLatest { pagingData ->
                locationAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeSearchLocation(name: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchLocation(name).collectLatest { pagingData ->
                locationAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecyclerView() = with(vb) {
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = locationAdapter.withLoadStateFooter(footer = loadStateAdapter)
        }
        centralizeRetryButton()
    }

    private fun onItemClick(result: ResultLocation) {
        toast(result.id.toString())
    }

    private fun centralizeRetryButton() {
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == locationAdapter.itemCount && loadStateAdapter.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }
    }
}