package com.joyner.marvelapp.data.models.local

data class MarvelCharacter(
    val id: Int = 0,
    val name: String = "",
    val thumbnail: String = "",
    val numberOfComics: Int = 0,
    val numberOfSeries: Int = 0,
    val numberOfStories: Int = 0,
    val numberOfEvents: Int = 0,
)