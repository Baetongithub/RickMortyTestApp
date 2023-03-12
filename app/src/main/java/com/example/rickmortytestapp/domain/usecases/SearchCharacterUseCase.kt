package com.example.rickmortytestapp.domain.usecases

import com.example.rickmortytestapp.domain.repository.CharacterRepository

class SearchCharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    fun searchCharacter(name: String?, status: String?, species: String?, gender: String?) =
        characterRepository.searchAndFilterCharacter(name, status, species, gender)
}