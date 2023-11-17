package com.kirik.repository.ui.screen.search

sealed interface SearchUiState {

    val isLoading: Boolean
    val errorMessages: List<String>
    val searchInput: String

    data class NoPosts(
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
        override val searchInput: String
    ) : SearchUiState
}