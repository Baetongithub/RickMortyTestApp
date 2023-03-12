package com.example.rickmortytestapp.presentation.ui.fragments.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode
import com.example.rickmortytestapp.domain.usecases.GetEpisodesUseCase
import com.example.rickmortytestapp.domain.usecases.SearchEpisodeUseCase
import kotlinx.coroutines.flow.Flow

class EpisodesViewModel(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val searchEpisodeUseCase: SearchEpisodeUseCase
) : ViewModel() {

    private var _result: Flow<PagingData<ResultEpisode>>? = null

    fun searchEpisode(name: String): Flow<PagingData<ResultEpisode>> {
        val lastResult = _result
        if (lastResult != null) {
            return lastResult
        }

        val result = searchEpisodeUseCase.searchEpisode(name)
            .cachedIn(viewModelScope)
        _result = result
        return result
    }

    val getEpisodes = getEpisodesUseCase.getEpisodes()
}