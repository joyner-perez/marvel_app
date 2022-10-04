package com.joyner.marvelapp.ui.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object CharactersScreen: AppScreens("characters_screen")
    object DetailCharacterScreen: AppScreens("detail_character_screen")
}
