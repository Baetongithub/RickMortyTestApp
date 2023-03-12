package com.example.rickmortytestapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.rickmortytestapp.domain.usecases.*
import org.koin.dsl.module

@ExperimentalPagingApi
val domainModule = module {
    single { GetCharactersUseCase(characterRepository = get()) }
    single { GetLocationUseCase(locationRepository = get()) }
    single { GetEpisodesUseCase(episodeRepository = get()) }
    factory { SearchCharacterUseCase(characterRepository = get()) }
    factory { SearchEpisodeUseCase(episodeRepository = get()) }
    factory { SearchLocationUseCase(locationRepository = get()) }
}