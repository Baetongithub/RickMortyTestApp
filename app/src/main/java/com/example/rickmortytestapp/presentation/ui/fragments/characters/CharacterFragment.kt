package com.example.rickmortytestapp.presentation.ui.fragments.characters

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.rickmortytestapp.R
import com.example.rickmortytestapp.databinding.FragmentCharatcerBinding
import com.example.rickmortytestapp.databinding.ItemDialogSearchBinding
import com.example.rickmortytestapp.databinding.ItemFilterDialogBinding
import com.example.rickmortytestapp.presentation.extensions.toast
import com.example.rickmortytestapp.presentation.ui.base.BaseFragment
import com.example.rickmortytestapp.presentation.ui.fragments.load_state.MyLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment :
    BaseFragment<FragmentCharatcerBinding>(FragmentCharatcerBinding::inflate) {

    private val viewModel: CharactersViewModel by viewModel()
    private val rickMortyAdapter = CharacterAdapter(this::onItemClick)

    private val loadStateAdapter = MyLoadStateAdapter { rickMortyAdapter.retry() }
    private val gridLayoutManager = GridLayoutManager(context, 2)

    private var isFiltered = false

    override fun initView() {
        super.initView()
        setupRecyclerView()
    }

    override fun initViewModel() {
        super.initViewModel()
        getAllDefaultCharacters()

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.search_by_name -> {
                        showSearchDialog()
                        true
                    }
                    R.id.filter_character -> {
                        showFilterDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun getAllDefaultCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters.collect { pagingData ->
                rickMortyAdapter.submitData(pagingData)
            }
        }
    }

    private fun searchAndFilterCharacter(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchCharacter(name, status, species, gender).collectLatest { pagingData ->
                rickMortyAdapter.submitData(pagingData)
            }
        }
    }

    private fun filter(dialogBinding: ItemFilterDialogBinding) {
        val selectedStatusBtn = dialogBinding.radioGroupStatus.checkedRadioButtonId
        val selectedSpeciesBtn = dialogBinding.radioGroupRace.checkedRadioButtonId
        val selectedGenderBtn = dialogBinding.radioGroupGender.checkedRadioButtonId

        if (selectedStatusBtn != -1 && selectedSpeciesBtn != -1 && selectedGenderBtn != -1) {
            val rbStatus: RadioButton =
                dialogBinding.radioGroupStatus.findViewById(selectedStatusBtn)
            val rbSpecies: RadioButton =
                dialogBinding.radioGroupRace.findViewById(selectedSpeciesBtn)
            val rbGender: RadioButton =
                dialogBinding.radioGroupGender.findViewById(selectedGenderBtn)

            searchAndFilterCharacter(
                status = rbStatus.text.toString(),
                species = rbSpecies.text.toString(),
                gender = rbGender.text.toString()
            )
            with(vb) {
                tvFilterStatus.text =
                    String.format("${getString(R.string.status)}:\n${rbStatus.text}")
                tvFilterSpecies.text =
                    String.format("${getString(R.string.species)}:\n${rbSpecies.text}")
                tvFilterGender.text =
                    String.format("${getString(R.string.gender)}:\n${rbGender.text}")
            }
        }
        clearFilter()
    }

    private fun clearFilter() = with(vb) {
        btnClearFilter.setOnClickListener {
            cardViewClear.visibility = GONE
            isFiltered = false
            getAllDefaultCharacters()
        }
    }

    private fun showFilterDialog() {
        val dialogBinding = ItemFilterDialogBinding.inflate(activity?.layoutInflater!!)

        val dialog = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogBinding.root)
                .setPositiveButton(getString(R.string.apply)) { _, _ ->
                    filter(dialogBinding)
                    vb.cardViewClear.visibility = VISIBLE
                    isFiltered = true
                }
        }
        dialog?.create()?.show()
    }

    private fun showSearchDialog() {
        val dialogVb = activity?.layoutInflater?.let { ItemDialogSearchBinding.inflate(it) }

        val dialog = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogVb?.root)
                .setPositiveButton(getString(R.string.search)) { _, _ ->
                    searchAndFilterCharacter(name = dialogVb?.etSearchByName?.text.toString())
                }
        }
        dialog?.create()?.show()
    }

    private fun setupRecyclerView() = with(vb) {
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = rickMortyAdapter.withLoadStateFooter(footer = loadStateAdapter)

            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy < 1 && isFiltered) {
                        cardViewClear.visibility = VISIBLE
                    } else cardViewClear.visibility = GONE
                }
            })
        }
        centralizeRetryButton()
        dataLoadStateListener()
    }

    private fun dataLoadStateListener() = with(vb) {
        rickMortyAdapter.addLoadStateListener { loadStates ->
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            btnRetry.isVisible = loadStates.refresh is LoadState.Error
        }
        btnRetry.setOnClickListener { rickMortyAdapter.retry() }
    }

    private fun onItemClick(resultCharacter: com.example.rickmortytestapp.domain.model.character.ResultCharacter) {
        toast(resultCharacter.id.toString())
    }

    private fun centralizeRetryButton() {
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == rickMortyAdapter.itemCount && loadStateAdapter.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }
    }
}

