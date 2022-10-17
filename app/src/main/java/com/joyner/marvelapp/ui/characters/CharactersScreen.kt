package com.joyner.marvelapp.ui.characters

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.joyner.marvelapp.R
import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.domain.models.Response
import com.joyner.marvelapp.ui.common.LoadingScreen

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetailCharacterScreen: (characterId: Int) -> Unit
) {
    val context = LocalContext.current

    when(val marvelCharacters = charactersViewModel.marvelCharacters) {
        is Response.Loading -> LoadingScreen()
        is Response.Success -> if (marvelCharacters.data.isNotEmpty()) {
            Characters(marvelCharacters.data, navigateToDetailCharacterScreen)
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            Log.e("RESPONSE", "ERROR")
            Toast.makeText(context, R.string.error_get_marvel_characters, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun Characters(
    data: List<MarvelCharacter>,
    navigateToDetailCharacterScreen: (characterId: Int) -> Unit
) {
    LazyColumn() {
        items(
            items = data,
            key = { character ->
                character.id
            }
        ) { message ->
            CharacterItem(message, navigateToDetailCharacterScreen)
        }
    }
}