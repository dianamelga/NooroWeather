package com.dianascode.nooroweather.ui.common.navigation


sealed class NavDestinations(val route: String) {
    data object Home : NavDestinations("home")

    // add other destinations as needed
}