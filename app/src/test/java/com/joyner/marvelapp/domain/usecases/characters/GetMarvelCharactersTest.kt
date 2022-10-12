package com.joyner.marvelapp.domain.usecases.characters

import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.main.Response
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetMarvelCharactersTest {

    @RelaxedMockK
    lateinit var getMarvelCharactersUseCase: GetMarvelCharacters

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when the api doesn't return anything`() = runBlocking{
        var result: Response<List<MarvelCharacter>> = Response.Success(emptyList())
        val responseFlow = getMarvelCharactersUseCase()
        responseFlow.collect { response ->
            result = response
        }

        when(result) {
            is Response.Success -> assert((result as Response.Success<List<MarvelCharacter>>).data.isEmpty())
            else -> assert(false)
        }
    }

    @Test
    fun `when the api return anything`() = runBlocking{
        val fakeList: MutableList<MarvelCharacter> = mutableListOf()
        fakeList.add(MarvelCharacter(
            0,
            "",
            "",
            0,
            0,
            0,
            0
        ))
        var result: Response<List<MarvelCharacter>> = Response.Success(fakeList)
        val responseFlow = getMarvelCharactersUseCase()
        responseFlow.collect { response ->
            result = response
        }

        when(result) {
            is Response.Success -> assert((result as Response.Success<List<MarvelCharacter>>).data.isNotEmpty())
            else -> assert(false)
        }
    }
}