package com.example.rickmortytestapp.domain.usecases

import androidx.paging.ExperimentalPagingApi
import com.example.rickmortytestapp.domain.repository.CharacterRepository

@ExperimentalPagingApi
class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    fun getCharacters() = characterRepository.getDefaultCharacters()
}