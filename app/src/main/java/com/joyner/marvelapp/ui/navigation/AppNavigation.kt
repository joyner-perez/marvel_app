package com.joyner.marvelapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joyner.marvelapp.ui.characters.CharactersScreen
import com.joyner.marvelapp.ui.detailcharacter.DetailCharacterScreen
import com.joyner.marvelapp.ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(
                navigateToCharactersScreen = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.CharactersScreen.route)
                }
            )
        }
        composable(AppScreens.CharactersScreen.route) {
            CharactersScreen(
                navigateToDetailCharacterScreen = {
                    navController.navigate(AppScreens.CharactersScreen.route)
                }
            )
        }
        composable(AppScreens.DetailCharacterScreen.route) {
            DetailCharacterScreen(
                navigateToCharactersScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}