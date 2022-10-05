package com.joyner.marvelapp.data.services

import com.joyner.marvelapp.data.clients.MarvelClient
import com.joyner.marvelapp.data.models.main.HashApi
import com.joyner.marvelapp.data.models.response.MarvelCharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelService @Inject constructor(
    private val marvelClient: MarvelClient
) {
    suspend fun getMarvelCharacters(): Response<MarvelCharactersResponse> {
        return withContext(Dispatchers.IO) {
            val marvelRequest = HashApi.createMarvelApiHash()
            marvelClient.getMarvelCharacters(marvelRequest.apiKey, marvelRequest.ts, marvelRequest.hash)
        }
    }
}