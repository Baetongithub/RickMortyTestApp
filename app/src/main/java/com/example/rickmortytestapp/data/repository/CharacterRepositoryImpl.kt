package com.example.rickmortytestapp.data.repository

import androidx.paging.*
import com.example.rickmortytestapp.data.local.RickMortyDatabase
import com.example.rickmortytestapp.data.mappers.toCharacter
import com.example.rickmortytestapp.data.remote.paging.character.CharacterRemoteMediatorSource
import com.example.rickmortytestapp.data.remote.paging.character.SearchCharacterPagingSource
import com.example.rickmortytestapp.data.remote.RickMortyAPI
import com.example.rickmortytestapp.domain.model.character.ResultCharacter
import com.example.rickmortytestapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val rickMortyAPI: RickMortyAPI,
    private val rickMortyDatabase: RickMortyDatabase
) : CharacterRepository {
    override fun searchAndFilterCharacter(
        queryName: String?,
        status: String?,
        species: String?,
        gender: String?
    ): Flow<PagingData<ResultCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = {
                SearchCharacterPagingSource(
                    rickMortyAPI = rickMortyAPI,
                    name = queryName,
                    status = status,
                    species = species,
                    gender = gender
                )
            }
        ).flow
    }

    @ExperimentalPagingApi
    override fun getDefaultCharacters(): Flow<PagingData<ResultCharacter>> {
        val pagingSourceFactory = { rickMortyDatabase.getRickMortyDao().getCharacters() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CharacterRemoteMediatorSource(rickMortyAPI, rickMortyDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { entityPagingData -> entityPagingData.map { it.toCharacter() } }
    }

    private companion object {
        const val ITEMS_PER_PAGE = 5
    }
}