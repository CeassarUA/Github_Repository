package com.kirik.repository.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kirik.repository.ui.screen.search.SearchRoute
import com.kirik.repository.ui.screen.search.SearchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RepositoryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RepositoryDestinations.SearchRoute.route,
) {
    val navigationActions = RepositoryDestinations.NavigationActions(navController)
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = RepositoryDestinations.SearchRoute.route
        ) {
            val searchViewModel: SearchViewModel = getViewModel()
            SearchRoute(
                viewModel = searchViewModel
            ) {
            }
        }
    }
}
