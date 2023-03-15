package com.example.rickmortytestapp.domain.repository

import androidx.paging.PagingData
import com.example.rickmortytestapp.domain.model.location.ResultLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun searchLocationByName(queryName: String): Flow<PagingData<ResultLocation>>
    fun getDefaultLocations():Flow<PagingData<ResultLocation>>
}