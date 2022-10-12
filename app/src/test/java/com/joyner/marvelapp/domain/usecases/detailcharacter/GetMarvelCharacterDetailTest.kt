package com.joyner.marvelapp.domain.usecases.detailcharacter

import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.domain.models.Response
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMarvelCharacterDetailTest {

    @RelaxedMockK
    lateinit var getMarvelCharacterDetailUseCase: GetMarvelCharacterDetail

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when the api doesn't return anything`() = runBlocking{
        var result: Response<MarvelCharacter> = Response.Success(MarvelCharacter(
            0,
            "",
            "",
            0,
            0,
            0,
            0
        ))
        val responseFlow = getMarvelCharacterDetailUseCase(1211111111)
        responseFlow.collect { response ->
            result = response
        }

        when(result) {
            is Response.Success -> assert((result as Response.Success<MarvelCharacter>).data.id == 0)
            else -> assert(false)
        }
    }

    @Test
    fun `when the api return anything`() = runBlocking{
        val fake = MarvelCharacter(
            1011335,
            "",
            "",
            0,
            0,
            0,
            0
        )
        var result: Response<MarvelCharacter> = Response.Success(fake)
        val responseFlow = getMarvelCharacterDetailUseCase(1011335)
        responseFlow.collect { response ->
            result = response
        }

        when(result) {
            is Response.Success -> assert((result as Response.Success<MarvelCharacter>).data.id == 1011335)
            else -> assert(false)
        }
    }
}