package com.example.rickmortytestapp.presentation.ui.fragments.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortytestapp.domain.model.location.ResultLocation
import com.example.rickmortytestapp.domain.usecases.GetLocationUseCase
import com.example.rickmortytestapp.domain.usecases.SearchLocationUseCase
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class LocationViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val searchLocationUseCase: SearchLocationUseCase
) : ViewModel() {
    val getLocations = getLocationUseCase.getLocation()

    private var _result: Flow<PagingData<ResultLocation>>? = null

    fun searchLocation(name: String): Flow<PagingData<ResultLocation>> {
        val lastResult = _result
        if (lastResult != null) {
            return lastResult
        }

        val result = searchLocationUseCase.searchLocation(name)
            .cachedIn(viewModelScope)
        _result = result
        return result
    }
}