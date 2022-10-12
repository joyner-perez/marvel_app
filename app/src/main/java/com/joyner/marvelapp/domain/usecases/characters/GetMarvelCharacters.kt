package com.joyner.marvelapp.domain.usecases.characters

import com.joyner.marvelapp.domain.repositories.MarvelRepository
import javax.inject.Inject

class GetMarvelCharacters @Inject constructor(private val repository: MarvelRepository) {
    operator fun invoke() = repository.getMarvelCharacters()
}