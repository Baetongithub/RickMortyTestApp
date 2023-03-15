package com.example.rickmortytestapp.data.repository

import androidx.paging.*
import com.example.rickmortytestapp.data.local.RickMortyDatabase
import com.example.rickmortytestapp.data.mappers.toLocation
import com.example.rickmortytestapp.data.remote.paging.location.LocationMediatorSource
import com.example.rickmortytestapp.data.remote.paging.location.SearchLocationPagingSource
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.domain.model.location.ResultLocation
import com.example.rickmortytestapp.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val rickMortyAPI: RickMortyAPI,
    private val rickMortyDatabase: RickMortyDatabase
) : LocationRepository {

    override fun searchLocationByName(queryName: String): Flow<PagingData<ResultLocation>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { SearchLocationPagingSource(rickMortyAPI, queryName) }
        ).flow
    }

    @ExperimentalPagingApi
    override fun getDefaultLocations(): Flow<PagingData<ResultLocation>> {
        val pagingSourceFactory = { rickMortyDatabase.getLocationDao().getAllLocation() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            remoteMediator = LocationMediatorSource(rickMortyAPI, rickMortyDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { entityPagingData -> entityPagingData.map { it.toLocation() } }
    }

    private companion object {
        const val ITEMS_PER_PAGE = 10
    }
}