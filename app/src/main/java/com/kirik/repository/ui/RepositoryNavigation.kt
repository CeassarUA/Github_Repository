package com.kirik.repository.ui

import androidx.navigation.NavHostController

sealed class RepositoryDestinations(val route: String) {
    object SearchRoute : RepositoryDestinations("search")

    class NavigationActions(navController: NavHostController) {
        val navigateToHome: () -> Unit = {
            navController.navigate(RepositoryDestinations.SearchRoute.route)
        }
    }
}