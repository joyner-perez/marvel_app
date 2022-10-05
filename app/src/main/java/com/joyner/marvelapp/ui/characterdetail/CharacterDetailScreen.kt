package com.joyner.marvelapp.ui.characterdetail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joyner.marvelapp.R
import com.joyner.marvelapp.data.models.local.MarvelCharacter
import com.joyner.marvelapp.data.models.main.Response
import com.joyner.marvelapp.ui.characters.CharacterImage
import com.pollenweather.ui.common.LoadingScreen

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = hiltViewModel(),
    navigateToCharactersScreen: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.title_character_detail_screen),) },
                navigationIcon = {
                    IconButton(onClick = { navigateToCharactersScreen() }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Back button")
                    }
                }
            )
        }
    ) { contentPadding ->
        when(val marvelCharacters = characterDetailViewModel.marvelCharacter) {
            is Response.Loading -> LoadingScreen()
            is Response.Success -> CharacterDetail(contentPadding, marvelCharacters.data)
            is Response.Failure -> LaunchedEffect(Unit) {
                Log.e("RESPONSE", "ERROR")
                Toast.makeText(context, R.string.error_get_marvel_characters, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun CharacterDetail(contentPadding: PaddingValues, marvelCharacter: MarvelCharacter) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(0.5f, true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CharacterImage(
                urlImage = marvelCharacter.thumbnail,
                size = 250.dp
            )
            Text(
                style = MaterialTheme.typography.h1,
                text = marvelCharacter.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f, true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    style = MaterialTheme.typography.h1,
                    text = stringResource(
                        id = R.string.text_number_of_comics, marvelCharacter.numberOfComics
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    style = MaterialTheme.typography.h1,
                    text = stringResource(
                        id = R.string.text_number_of_series, marvelCharacter.numberOfSeries
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    style = MaterialTheme.typography.h1,
                    text = stringResource(
                        id = R.string.text_number_of_stories, marvelCharacter.numberOfStories
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    style = MaterialTheme.typography.h1,
                    text = stringResource(
                        id = R.string.text_number_of_events, marvelCharacter.numberOfEvents
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CharacterDetail(
        contentPadding = PaddingValues(all = 16.dp),
        MarvelCharacter(
        id = 1,
        name = "Heroe name",
        thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
        numberOfStories = 2,
        numberOfSeries = 4,
        numberOfEvents = 6,
        numberOfComics = 8
    ))
}