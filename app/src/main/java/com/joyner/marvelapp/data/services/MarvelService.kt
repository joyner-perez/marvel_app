package com.joyner.marvelapp.data.services

import com.joyner.marvelapp.data.clients.MarvelClient
import com.joyner.marvelapp.data.models.main.HashApi
import com.joyner.marvelapp.data.models.response.MarvelCharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelService @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getMarvelCharacters(): Response<MarvelCharactersResponse> {
        return withContext(Dispatchers.IO) {
            val marvelRequest = HashApi.createMarvelApiHash()
            retrofit
                .create(MarvelClient::class.java)
                .getMarvelCharacters(marvelRequest.apiKey, marvelRequest.ts, marvelRequest.hash)
        }
    }
}