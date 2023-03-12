package com.example.rickmortytestapp.domain.usecases

import androidx.paging.ExperimentalPagingApi
import com.example.rickmortytestapp.domain.repository.LocationRepository

@ExperimentalPagingApi
class GetLocationUseCase(
    private val locationRepository: LocationRepository
) {
    fun getLocation() = locationRepository.getDefaultLocations()
}