package com.kirik.repository.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.kirik.repository.domain.model.Repository

sealed class RepositoryDestinations(val route: String) {
    object SearchRoute : RepositoryDestinations("search")
    object DetailRoute : RepositoryDestinations("details") {
        const val OWNER = "OWNER"
        const val NAME = "NAME"

        fun createRoute(repository: Repository) = route + "/${repository.fullName}"
    }
}

class NavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(RepositoryDestinations.SearchRoute.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToDetails: (repository: Repository) -> Unit = {
        navController.navigate(RepositoryDestinations.DetailRoute.createRoute(repository = it)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
