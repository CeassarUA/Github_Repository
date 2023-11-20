/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kirik.repository.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kirik.repository.ui.screen.details.DetailsRoute
import com.kirik.repository.ui.screen.details.DetailsScreen
import com.kirik.repository.ui.screen.details.DetailsViewModel
import com.kirik.repository.ui.screen.search.SearchRoute
import com.kirik.repository.ui.screen.search.SearchViewModel
import org.koin.androidx.compose.getViewModel

const val POST_ID = "postId"

@Composable
fun RepositoryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RepositoryDestinations.SearchRoute.route,
) {
    val navigationActions = NavigationActions(navController)
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = RepositoryDestinations.SearchRoute.route
        ) { navBackStackEntry ->
            val searchViewModel: SearchViewModel = getViewModel()
            SearchRoute(
                viewModel = searchViewModel
            ) {
                navigationActions.navigateToDetails(it)
            }
        }
        composable(
            route = "details/{${RepositoryDestinations.DetailRoute.OWNER}}/{${RepositoryDestinations.DetailRoute.NAME}}",
            arguments = listOf(
                navArgument(RepositoryDestinations.DetailRoute.OWNER) {
                    type = NavType.StringType
                },
                navArgument(RepositoryDestinations.DetailRoute.NAME) {
                    type = NavType.StringType
                })
        ) { navBackStackEntry ->
            val viewModel: DetailsViewModel = getViewModel()
            DetailsRoute(viewModel, modifier = Modifier.fillMaxSize())
        }
    }
}
