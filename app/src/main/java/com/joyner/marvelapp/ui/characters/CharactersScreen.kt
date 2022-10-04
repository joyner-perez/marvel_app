package com.joyner.marvelapp.ui.characters

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.joyner.marvelapp.data.models.main.Response
import com.pollenweather.ui.common.LoadingScreen

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetailCharacterScreen: () -> Unit
) {
    when(val marvelCharacters = charactersViewModel.marvelCharacters) {
        is Response.Loading -> LoadingScreen()
        is Response.Success -> if (marvelCharacters.data.isNotEmpty()) {
            LaunchedEffect(marvelCharacters.data) {
                Log.i("RESPONSE", "${marvelCharacters.data.size}")
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            Log.e("RESPONSE", "ERROR")
        }
    }
}