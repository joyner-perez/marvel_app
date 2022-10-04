package com.joyner.marvelapp.data.repositoriesimpl

import com.joyner.marvelapp.data.models.main.Response
import com.joyner.marvelapp.data.models.mappers.MarvelMapper.toMarvelCharacters
import com.joyner.marvelapp.data.services.MarvelService
import com.joyner.marvelapp.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val marvelService: MarvelService
): MarvelRepository {
    override fun getMarvelCharacters() = channelFlow {
        try {
            send(Response.Loading)
            val marvelCharactersResponseApi = marvelService.getMarvelCharacters()
            val marvelCharactersResponse = marvelCharactersResponseApi.body()
            if (marvelCharactersResponseApi.isSuccessful && marvelCharactersResponse!!.code == 200) {
                val listMarvelCharecters = List(marvelCharactersResponse.data.results.size) {
                    marvelCharactersResponse.data.results[it].toMarvelCharacters()
                }
                send(Response.Success(listMarvelCharecters))
            } else {
                send(Response.Success(emptyList()))
            }
        } catch (e: Exception) {
            send(Response.Failure(e))
        }
    }

}