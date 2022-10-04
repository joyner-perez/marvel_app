package com.joyner.marvelapp.data.clients

import com.joyner.marvelapp.data.models.response.MarvelCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelClient {
    @GET("characters")
    suspend fun getMarvelCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ) : Response<MarvelCharactersResponse>
}