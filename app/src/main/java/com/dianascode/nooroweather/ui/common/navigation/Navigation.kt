package com.dianascode.nooroweather.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dianascode.nooroweather.ui.features.home.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavDestinations.Home.route) {
        composable(NavDestinations.Home.route) {
            HomeScreen(navController = navController, modifier = modifier)
        }
    }
}