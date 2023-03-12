package com.example.rickmortytestapp.data.remote.paging.episode

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickmortytestapp.data.local.RickMortyDatabase
import com.example.rickmortytestapp.data.local.model.ResultEpisodeEntity
import com.example.rickmortytestapp.data.local.model.RickMortyRemoteKeys
import com.example.rickmortytestapp.data.remote.RickMortyAPI

@ExperimentalPagingApi
class GetDefaultEpisodesPagingSource(
    private val rickMortyApi: RickMortyAPI,
    private val rickMortyDatabase: RickMortyDatabase
) : RemoteMediator<Int, ResultEpisodeEntity>() {

    private val rickMortyDao = rickMortyDatabase.getEpisodeDao()
    private val remoteKeysDao = rickMortyDatabase.getRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ResultEpisodeEntity>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestRemoteKeyToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: START_PAGE
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }
            val response = rickMortyApi.getEpisodes(page = currentPage)
            val endOfPaginationReached = response.resultEpisodes.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            rickMortyDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    rickMortyDao.deleteAllEpisode()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.resultEpisodes.map { result ->
                    RickMortyRemoteKeys(
                        id = result.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.insertAllRemoteKeys(remoteKeys = keys)

                rickMortyDao.insertAllEpisode(response.resultEpisodes)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getClosestRemoteKeyToCurrentPosition(
        state: PagingState<Int, ResultEpisodeEntity>
    ): RickMortyRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id.toString())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ResultEpisodeEntity>
    ): RickMortyRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { result ->
                remoteKeysDao.getRemoteKeys(id = result.id.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ResultEpisodeEntity>
    ): RickMortyRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episodeEntity ->
                remoteKeysDao.getRemoteKeys(id = episodeEntity.id.toString())
            }
    }

    private companion object {
        const val START_PAGE = 1
    }
}