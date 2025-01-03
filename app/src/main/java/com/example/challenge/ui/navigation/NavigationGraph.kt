package com.example.challenge.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.challenge.ui.screens.FavoriteScreen
import com.example.challenge.ui.screens.MainScreen
import com.example.challenge.ui.viewmodel.MainViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object FavoriteScreen : Screen("favorites")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: MainViewModel = koinViewModel()

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() }
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController, viewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController, viewModel)
        }
    }
}
