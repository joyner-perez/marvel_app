package com.joyner.marvelapp.domain.usecases.characters

import com.joyner.marvelapp.domain.repositories.MarvelRepository

class GetMarvelCharacterDetail(
    private val repository: MarvelRepository
) {
    operator fun invoke(characterId: Int) = repository.getMarvelCharacterDetail(characterId)
}