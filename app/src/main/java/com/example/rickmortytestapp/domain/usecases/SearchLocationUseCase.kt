package com.example.rickmortytestapp.domain.usecases

import com.example.rickmortytestapp.domain.repository.LocationRepository

class SearchLocationUseCase(
    private val locationRepository: LocationRepository
) {
    fun searchLocation(name: String) = locationRepository.searchLocationByName(name)
}