package com.joyner.marvelapp.domain.usecases

import com.joyner.marvelapp.domain.usecases.characters.GetMarvelCharacters
import com.joyner.marvelapp.domain.usecases.detailcharacter.GetMarvelCharacterDetail

data class MarvelUseCases(
    val getMarvelCharacters: GetMarvelCharacters,
    val getMarvelCharacterDetail: GetMarvelCharacterDetail
)
