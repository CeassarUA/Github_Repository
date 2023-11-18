package com.kirik.repository.ui.screen.search

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import com.kirik.repository.ui.model.Repository
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue


@Composable
internal fun HomeRoute(
    uiState: SearchUiState,

    viewModel: SearchViewModel
) {
    val homeListLazyListState = rememberLazyListState()
    val mockList = listOf(
        Repository(
            1, "1", 1
        )
    )
    val articleDetailLazyListStates = when (uiState) {
        is SearchUiState.NoPosts -> emptyList()
        else -> mockList
    }.associate { post ->
        key(post.id) {
            post.id to rememberLazyListState()
        }
    }
    SearchScreen(state = uiState, viewModel)

}

@Composable
fun HomeRoute(
    homeViewModel: SearchViewModel,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    // UiState of the HomeScreen
    val uiState: SearchUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeRoute(
        uiState,
        homeViewModel
    )
}


