package com.example.rickmortytestapp.di

import com.example.rickmortytestapp.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetCharactersUseCase(characterRepository = get()) }
    factory { GetLocationUseCase(locationRepository = get()) }
    factory { GetEpisodesUseCase(episodeRepository = get()) }
    factory { SearchCharacterUseCase(characterRepository = get()) }
    factory { SearchEpisodeUseCase(episodeRepository = get()) }
    factory { SearchLocationUseCase(locationRepository = get()) }
}