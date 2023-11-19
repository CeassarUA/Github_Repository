package com.kirik.repository.ui.screen.search

import android.util.Log
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
    Log.d("HomeRoute", uiState.toString())
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


