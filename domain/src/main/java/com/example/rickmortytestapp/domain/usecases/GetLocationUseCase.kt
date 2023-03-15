package com.example.rickmortytestapp.domain.usecases

import com.example.rickmortytestapp.domain.repository.LocationRepository

class GetLocationUseCase(
    private val locationRepository: LocationRepository
) {
    fun getLocation() = locationRepository.getDefaultLocations()
}