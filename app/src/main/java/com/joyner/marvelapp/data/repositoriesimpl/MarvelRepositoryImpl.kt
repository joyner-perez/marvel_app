package com.joyner.marvelapp.data.repositoriesimpl

import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.main.Response
import com.joyner.marvelapp.data.models.mappers.MarvelMapper.toMarvelCharacters
import com.joyner.marvelapp.data.services.MarvelService
import com.joyner.marvelapp.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val marvelService: MarvelService
): MarvelRepository {
    override fun getMarvelCharacters() = flow {
        try {
            emit(Response.Loading)
            val marvelCharactersResponseApi = marvelService.getMarvelCharacters()
            val marvelCharactersResponse = marvelCharactersResponseApi.body()
            if (marvelCharactersResponseApi.isSuccessful && marvelCharactersResponse!!.code == 200) {
                val listMarvelCharecters = List(marvelCharactersResponse.data.results.size) {
                    marvelCharactersResponse.data.results[it].toMarvelCharacters()
                }
                emit(Response.Success(listMarvelCharecters))
            } else {
                emit(Response.Success(emptyList()))
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun getMarvelCharacterDetail(characterId: Int) = flow {
        try {
            emit(Response.Loading)
            val marvelCharactersResponseApi = marvelService.getMarvelCharacterDetail(characterId)
            val marvelCharactersResponse = marvelCharactersResponseApi.body()
            if (marvelCharactersResponseApi.isSuccessful && marvelCharactersResponse!!.code == 200) {
                emit(Response.Success(marvelCharactersResponse.data.results[0].toMarvelCharacters()))
            } else {
                emit(Response.Success(MarvelCharacter()))
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}