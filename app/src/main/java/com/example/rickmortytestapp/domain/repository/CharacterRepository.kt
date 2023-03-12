package com.example.rickmortytestapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.example.rickmortytestapp.domain.model.character.ResultCharacter

interface CharacterRepository {

    fun searchAndFilterCharacter(
        queryName: String?,
        status: String?,
        species: String?,
        gender: String?
    ): Flow<PagingData<ResultCharacter>>

    fun getDefaultCharacters(): Flow<PagingData<ResultCharacter>>
}