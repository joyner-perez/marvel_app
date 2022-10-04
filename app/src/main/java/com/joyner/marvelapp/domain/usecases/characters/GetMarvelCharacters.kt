package com.joyner.marvelapp.domain.usecases.characters

import com.joyner.marvelapp.domain.repositories.MarvelRepository

class GetMarvelCharacters(
    private val repository: MarvelRepository
) {
    operator fun invoke() = repository.getMarvelCharacters()
}