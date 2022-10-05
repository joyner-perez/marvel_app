package com.joyner.marvelapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joyner.marvelapp.ui.characterdetail.CharacterDetailScreen
import com.joyner.marvelapp.ui.characters.CharactersScreen
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
                    navController.navigate("${AppScreens.CharacterDetailScreen.route}/${it}")
                }
            )
        }
        composable(
            route = "${AppScreens.CharacterDetailScreen.route}/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {
            CharacterDetailScreen {
                navController.popBackStack()
            }
        }
    }
}