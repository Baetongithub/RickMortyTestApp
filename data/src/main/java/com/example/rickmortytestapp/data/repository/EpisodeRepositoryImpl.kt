package com.example.rickmortytestapp.data.repository

import androidx.paging.*
import com.example.rickmortytestapp.data.local.RickMortyDatabase
import com.example.rickmortytestapp.data.mappers.toEpisode
import com.example.rickmortytestapp.data.remote.paging.episode.GetDefaultEpisodesPagingSource
import com.example.rickmortytestapp.data.remote.paging.episode.SearchEpisodePagingSource
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.domain.model.episodes.ResultEpisode
import com.example.rickmortytestapp.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpisodeRepositoryImpl(
    private val rickMortyAPI: RickMortyAPI,
    private val rickMortyDatabase: RickMortyDatabase
) : EpisodeRepository {
    override fun searchEpisodeByName(queryName: String): Flow<PagingData<ResultEpisode>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { SearchEpisodePagingSource(rickMortyAPI, queryName) }
        ).flow
    }

    @ExperimentalPagingApi
    override fun getDefaultEpisode(): Flow<PagingData<ResultEpisode>> {
        val pagingSourceFactory = { rickMortyDatabase.getEpisodeDao().getAllEpisode() }
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = GetDefaultEpisodesPagingSource(rickMortyAPI, rickMortyDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { entityPagingData -> entityPagingData.map { it.toEpisode() } }
    }
}