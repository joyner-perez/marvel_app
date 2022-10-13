package com.joyner.marvelapp.data.services

import com.joyner.marvelapp.data.clients.MarvelClient
import com.joyner.marvelapp.data.models.response.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MarvelServiceTest {

    @Test
    fun `when the api returns anything`() = runBlocking {
        class FakeMarvelClient : MarvelClient {
            override suspend fun getMarvelCharacters(
                apiKey: String,
                ts: String,
                hash: String
            ): Response<MarvelCharactersResponse> {
                return Response.success(MarvelCharactersResponse(
                    "",
                    "",
                    200,
                    "",
                    Data(
                        0,
                        0,
                        0,
                        listOf(
                            Result(
                                Comics(0, "", emptyList(), 0),
                                "",
                                Events(0, "", emptyList(), 0),
                                0,
                                "",
                                "",
                                "",
                                Series(0, "", emptyList(), 0),
                                Stories(0, "", emptyList(), 0),
                                Thumbnail("", ""),
                                emptyList()
                            ),
                            Result(
                                Comics(0, "", emptyList(), 0),
                                "",
                                Events(0, "", emptyList(), 0),
                                0,
                                "",
                                "",
                                "",
                                Series(0, "", emptyList(), 0),
                                Stories(0, "", emptyList(), 0),
                                Thumbnail("", ""),
                                emptyList()
                            )
                        ),
                        0
                    ),
                    "",
                    ""
                ))
            }

            override suspend fun getMarvelCharacterDetail(
                characterId: Int,
                apiKey: String,
                ts: String,
                hash: String
            ): Response<MarvelCharactersResponse> {
                return Response.success(MarvelCharactersResponse(
                    "",
                    "",
                    200,
                    "",
                    Data(
                        0,
                        0,
                        0,
                        listOf(
                            Result(
                                Comics(0, "", emptyList(), 0),
                                "",
                                Events(0, "", emptyList(), 0),
                                0,
                                "",
                                "",
                                "",
                                Series(0, "", emptyList(), 0),
                                Stories(0, "", emptyList(), 0),
                                Thumbnail("", ""),
                                emptyList()
                            )
                        ),
                        0
                    ),
                    "",
                    ""
                ))
            }

        }

        val marvelService = MarvelService(
            marvelClient = FakeMarvelClient(),
            dispatcher = UnconfinedTestDispatcher()
        )

        var apiResponse = marvelService.getMarvelCharacters()
        assert(apiResponse.isSuccessful && apiResponse.code() == 200)
        var marvelCharactersResponse = apiResponse.body()
        assertEquals(2, marvelCharactersResponse!!.data.results.size)

        apiResponse = marvelService.getMarvelCharacterDetail(123456)
        assert(apiResponse.isSuccessful && apiResponse.code() == 200)
        marvelCharactersResponse = apiResponse.body()
        assertEquals(1, marvelCharactersResponse!!.data.results.size)
    }

    @Test
    fun `when the api returns nothing`() = runBlocking {
        class FakeMarvelClient : MarvelClient {
            override suspend fun getMarvelCharacters(
                apiKey: String,
                ts: String,
                hash: String
            ): Response<MarvelCharactersResponse> {
                return Response.error(
                    401,
                    "{\"code\":\"InvalidCredentials\"}".toResponseBody("application/json".toMediaTypeOrNull()
                    )
                )
            }

            override suspend fun getMarvelCharacterDetail(
                characterId: Int,
                apiKey: String,
                ts: String,
                hash: String
            ): Response<MarvelCharactersResponse> {
                return Response.error(
                    401,
                    "{\"code\":\"InvalidCredentials\"}".toResponseBody("application/json".toMediaTypeOrNull()
                    )
                )
            }

        }

        val marvelService = MarvelService(
            marvelClient = FakeMarvelClient(),
            dispatcher = UnconfinedTestDispatcher()
        )

        var apiResponse = marvelService.getMarvelCharacters()
        assert(!apiResponse.isSuccessful && apiResponse.code() == 401)

        apiResponse = marvelService.getMarvelCharacterDetail(123456)
        assert(!apiResponse.isSuccessful && apiResponse.code() == 401)
    }
}