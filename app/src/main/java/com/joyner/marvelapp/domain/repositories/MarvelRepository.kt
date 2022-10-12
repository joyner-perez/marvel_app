package com.joyner.marvelapp.domain.repositories

import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getMarvelCharacters(): Flow<Response<List<MarvelCharacter>>>

    fun getMarvelCharacterDetail(characterId: Int): Flow<Response<MarvelCharacter>>
}