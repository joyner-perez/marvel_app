package com.joyner.marvelapp.di

import com.joyner.marvelapp.data.clients.MarvelClient
import com.joyner.marvelapp.data.repositoriesimpl.MarvelRepositoryImpl
import com.joyner.marvelapp.data.services.MarvelService
import com.joyner.marvelapp.domain.repositories.MarvelRepository
import com.joyner.marvelapp.domain.usecases.detailcharacter.GetMarvelCharacterDetail
import com.joyner.marvelapp.domain.usecases.characters.GetMarvelCharacters
import com.joyner.marvelapp.domain.usecases.MarvelUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelModule {
    @Singleton
    @Provides
    fun provideMarvelRepository(
        marvelService: MarvelService
    ): MarvelRepository = MarvelRepositoryImpl(marvelService)

    @Singleton
    @Provides
    fun provideMarvelUseCases(
        repository: MarvelRepository
    ) = MarvelUseCases(
        getMarvelCharacters = GetMarvelCharacters(repository),
        getMarvelCharacterDetail = GetMarvelCharacterDetail(repository)
    )

    @Singleton
    @Provides
    fun provideMarvelClient(
        retrofit: Retrofit
    ): MarvelClient = retrofit.create(MarvelClient::class.java)
}