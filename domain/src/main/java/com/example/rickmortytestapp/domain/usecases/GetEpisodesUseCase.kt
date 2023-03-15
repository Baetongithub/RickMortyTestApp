package com.example.rickmortytestapp.domain.usecases

import com.example.rickmortytestapp.domain.repository.EpisodeRepository

class GetEpisodesUseCase(
    private val episodeRepository: EpisodeRepository
) {
    fun getEpisodes() = episodeRepository.getDefaultEpisode()
}