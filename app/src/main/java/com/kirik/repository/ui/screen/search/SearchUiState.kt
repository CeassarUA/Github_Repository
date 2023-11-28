package com.kirik.repository.ui.screen.search

sealed interface SearchUiState {
    data class NoItems(
        val isLoading: Boolean,
    ) : SearchUiState

    data class ItemsFounded(
        val isLoading: Boolean,
        val items : List<String>
    ) : SearchUiState
}