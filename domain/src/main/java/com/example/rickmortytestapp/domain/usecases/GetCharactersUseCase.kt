package com.example.rickmortytestapp.domain.usecases

import com.example.rickmortytestapp.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    fun getCharacters() = characterRepository.getDefaultCharacters()
}