package com.joyner.marvelapp.ui.characterdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.domain.models.Response
import com.joyner.marvelapp.domain.usecases.MarvelUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val marvelUseCases: MarvelUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var characterId = mutableStateOf(savedStateHandle.get<Int>("characterId"))
        private set
    var marvelCharacter by mutableStateOf<Response<MarvelCharacter>>(
        Response.Success(
        MarvelCharacter()
    ))
        private set

    init {
        getMarvelCharacterDetail(characterId.value!!)
    }

    fun getMarvelCharacterDetail(characterId: Int) = viewModelScope.launch {
        marvelUseCases.getMarvelCharacterDetail(characterId).collect { response ->
            marvelCharacter = response
        }
    }
}