package com.example.rickmortytestapp.di

import com.example.rickmortytestapp.presentation.ui.fragments.characters.CharactersViewModel
import com.example.rickmortytestapp.presentation.ui.fragments.episodes.EpisodesViewModel
import com.example.rickmortytestapp.presentation.ui.fragments.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CharactersViewModel(getCharactersUseCase = get(), searchCharacterUseCase = get()) }
    viewModel { LocationViewModel(getLocationUseCase = get(), searchLocationUseCase = get()) }
    viewModel { EpisodesViewModel(getEpisodesUseCase = get(), searchEpisodeUseCase = get()) }
}