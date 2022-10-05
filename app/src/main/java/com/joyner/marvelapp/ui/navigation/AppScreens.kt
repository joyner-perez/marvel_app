package com.joyner.marvelapp.ui.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object CharactersScreen: AppScreens("characters_screen")
    object CharacterDetailScreen: AppScreens("character_detail_screen")
}
