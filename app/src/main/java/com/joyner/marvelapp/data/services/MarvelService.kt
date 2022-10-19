package com.joyner.marvelapp.data.services

import com.joyner.marvelapp.data.clients.MarvelClient
import com.joyner.marvelapp.data.models.main.HashApi
import com.joyner.marvelapp.data.models.response.MarvelCharactersResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelService @Inject constructor(
    private val marvelClient: MarvelClient,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getMarvelCharacters(): Response<MarvelCharactersResponse> {
        val marvelRequest = HashApi.createMarvelApiHash()
        return marvelClient.getMarvelCharacters(marvelRequest.apiKey, marvelRequest.ts, marvelRequest.hash, 100)
    }

    suspend fun getMarvelCharacterDetail(characterId: Int): Response<MarvelCharactersResponse> {
        return withContext(dispatcher) {
            val marvelRequest = HashApi.createMarvelApiHash()
            marvelClient.getMarvelCharacterDetail(
                characterId,
                marvelRequest.apiKey,
                marvelRequest.ts,
                marvelRequest.hash
            )
        }
    }
}