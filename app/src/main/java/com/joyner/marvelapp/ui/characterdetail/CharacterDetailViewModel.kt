package com.joyner.marvelapp.ui.characterdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.main.Response
import com.joyner.marvelapp.domain.usecases.characters.MarvelUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterDetailState(
    val characterId: Int,
)

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
private val marvelUseCases: MarvelUseCases,
savedStateHandle: SavedStateHandle
): ViewModel() {
    var state = mutableStateOf(CharacterDetailState(0))
        private set
    var marvelCharacter by mutableStateOf<Response<MarvelCharacter>>(Response.Success(
        MarvelCharacter()
    ))
        private set

    init {
        val argument: Int = savedStateHandle["characterId"]!!
        state.value = state.value.copy(
            characterId = argument,
        )
        getMarvelCharacterDetail(state.value.characterId)
    }

    fun getMarvelCharacterDetail(characterId: Int) = viewModelScope.launch {
        marvelUseCases.getMarvelCharacterDetail(characterId).collect { response ->
            marvelCharacter = response
        }
    }
}