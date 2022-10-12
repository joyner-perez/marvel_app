package com.joyner.marvelapp.domain.usecases.detailcharacter

import com.joyner.marvelapp.domain.repositories.MarvelRepository
import javax.inject.Inject

class GetMarvelCharacterDetail@Inject constructor(private val repository: MarvelRepository) {
    operator fun invoke(characterId: Int) = repository.getMarvelCharacterDetail(characterId)
}