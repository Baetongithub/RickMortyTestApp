package com.example.rickmortytestapp.domain.repository

import androidx.paging.PagingData
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun searchEpisodeByName(queryName: String): Flow<PagingData<ResultEpisode>>
    fun getDefaultEpisode():Flow<PagingData<ResultEpisode>>
}