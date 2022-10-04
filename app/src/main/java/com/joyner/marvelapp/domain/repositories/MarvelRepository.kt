package com.joyner.marvelapp.domain.repositories

import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.main.Response
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getMarvelCharacters(): Flow<Response<List<MarvelCharacter>>>
}