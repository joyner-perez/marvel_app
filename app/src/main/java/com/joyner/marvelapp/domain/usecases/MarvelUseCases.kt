package com.joyner.marvelapp.domain.usecases

import com.joyner.marvelapp.domain.usecases.characters.GetMarvelCharacters
import com.joyner.marvelapp.domain.usecases.detailcharacter.GetMarvelCharacterDetail
import javax.inject.Inject

data class MarvelUseCases @Inject constructor(
    val getMarvelCharacters: GetMarvelCharacters,
    val getMarvelCharacterDetail: GetMarvelCharacterDetail
)
