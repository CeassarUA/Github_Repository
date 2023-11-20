package com.kirik.repository.ui.screen.search

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.kirik.repository.domain.model.Repository


@Composable
internal fun SearchRoute(
    uiState: SearchUiState,
    viewModel: SearchViewModel,
    onRepositoryClick: (Repository) -> Unit

) {
    Log.d("SearchRoute", uiState.toString())
    SearchScreen(state = uiState, viewModel, onRepositoryClick)
}

@Composable
fun SearchRoute(
    viewModel: SearchViewModel,
    onRepositoryClick: (Repository) -> Unit

) {
    val uiState: SearchUiState by viewModel.uiState.collectAsStateWithLifecycle()
    SearchRoute(
        uiState,
        viewModel,
        onRepositoryClick
    )
}



