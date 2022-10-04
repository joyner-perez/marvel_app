package com.joyner.marvelapp.di

import com.joyner.marvelapp.data.repositoriesimpl.MarvelRepositoryImpl
import com.joyner.marvelapp.data.services.MarvelService
import com.joyner.marvelapp.domain.repositories.MarvelRepository
import com.joyner.marvelapp.domain.usecases.characters.GetMarvelCharacters
import com.joyner.marvelapp.domain.usecases.characters.MarvelUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MarvelModule {
    @Provides
    fun provideMarvelRepository(
        marvelService: MarvelService
    ): MarvelRepository = MarvelRepositoryImpl(marvelService)

    @Provides
    fun provideMarvelUseCases(
        repository: MarvelRepository
    ) = MarvelUseCases(
        getMarvelCharacters = GetMarvelCharacters(repository)
    )
}