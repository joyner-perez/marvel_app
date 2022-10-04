package com.joyner.marvelapp.ui.characters

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.main.Response
import com.joyner.marvelapp.domain.usecases.characters.MarvelUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
private val marvelUseCases: MarvelUseCases
): ViewModel() {
    init {
        getMarvelCharacters()
    }

    var marvelCharacters by mutableStateOf<Response<List<MarvelCharacter>>>(Response.Success(emptyList()))
        private set

    fun getMarvelCharacters() = viewModelScope.launch {
        marvelUseCases.getMarvelCharacters().collect { response ->
            Log.i("RESPONSE", response.toString())
            marvelCharacters = response
        }
    }
}