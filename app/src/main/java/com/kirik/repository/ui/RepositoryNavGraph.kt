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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kirik.repository.ui.screen.search.HomeRoute
import com.kirik.repository.ui.screen.search.SearchViewModel

const val POST_ID = "postId"

@Composable
fun RepositoryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = RepositoryDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = RepositoryDestinations.HOME_ROUTE
        ) { navBackStackEntry ->
            val homeViewModel: SearchViewModel =
                SearchViewModel()
            HomeRoute(
                homeViewModel = homeViewModel
            )
        }
        composable(
            route = RepositoryDestinations.DETAILS_ROUTE
        ) { navBackStackEntry ->
            val homeViewModel =
                SearchViewModel()
            HomeRoute(
                homeViewModel = homeViewModel
            )
        }
    }
}
