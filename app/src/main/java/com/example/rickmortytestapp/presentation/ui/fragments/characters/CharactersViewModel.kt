package com.example.rickmortytestapp.presentation.ui.fragments.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortytestapp.domain.model.character.ResultCharacter
import com.example.rickmortytestapp.domain.usecases.GetCharactersUseCase
import com.example.rickmortytestapp.domain.usecases.SearchCharacterUseCase
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase
) : ViewModel() {
    val getCharacters = getCharactersUseCase.getCharacters()

    private var _result: Flow<PagingData<ResultCharacter>>? = null

    fun searchCharacter(name: String?, status: String?, species: String?, gender: String?): Flow<PagingData<ResultCharacter>> {
        val lastResult = _result
        if (lastResult != null) {
            return lastResult
        }

        val result = searchCharacterUseCase.searchCharacter(name, status,species, gender)
            .cachedIn(viewModelScope)
        _result = result
        return result
    }
}