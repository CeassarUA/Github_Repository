package com.kirik.repository.ui.screen.search

import com.kirik.repository.data.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

sealed interface SearchUiState {
    val searchInput: String


    data class NoPosts(
        val isLoading: Boolean,
        override val searchInput: String
    ) : SearchUiState

    data class PostFounded(
        val isLoading: Boolean,
        override val searchInput: String
    ) : SearchUiState
}