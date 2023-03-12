package com.example.rickmortytestapp.domain.usecases

import com.example.rickmortytestapp.domain.repository.EpisodeRepository

class SearchEpisodeUseCase(
    private val episodeRepository: EpisodeRepository
) {
    fun searchEpisode(name: String) = episodeRepository.searchEpisodeByName(name)
}