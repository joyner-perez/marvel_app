package com.joyner.marvelapp.di

import com.joyner.marvelapp.data.repositoriesimpl.MarvelRepositoryImpl
import com.joyner.marvelapp.data.services.MarvelService
import com.joyner.marvelapp.domain.repositories.MarvelRepository
import com.joyner.marvelapp.domain.usecases.MarvelUseCases
import com.joyner.marvelapp.domain.usecases.characters.GetMarvelCharacters
import com.joyner.marvelapp.domain.usecases.detailcharacter.GetMarvelCharacterDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
class MarvelModule {

    @Provides
    fun provideMarvelRepository(
        marvelService: MarvelService,
        dispatcherModule: CoroutineDispatcher
    ): MarvelRepository = MarvelRepositoryImpl(marvelService, dispatcherModule)

    @Provides
    fun provideMarvelUseCases(
        repository: MarvelRepository
    ) = MarvelUseCases(
        getMarvelCharacters = GetMarvelCharacters(repository),
        getMarvelCharacterDetail = GetMarvelCharacterDetail(repository)
    )
}