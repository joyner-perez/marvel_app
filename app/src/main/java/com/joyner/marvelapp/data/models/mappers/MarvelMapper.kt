package com.joyner.marvelapp.data.models.mappers

import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.response.Result

object MarvelMapper {
    fun Result.toMarvelCharacters() = MarvelCharacter(
        id = id,
        name = name,
        thumbnail = "${thumbnail.path}.${thumbnail.extension}",
        numberOfComics = comics.available,
        numberOfEvents = events.available,
        numberOfSeries = series.available,
        numberOfStories = stories.available
    )
}