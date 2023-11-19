package com.kirik.repository.ui.screen.search

import com.kirik.repository.data.model.RepositoryResponse

sealed interface SearchUiState {

    val isLoading: Boolean
    val searchInput: String

    data class NoPosts(
        override val isLoading: Boolean,
        override val searchInput: String
    ) : SearchUiState

    data class PostFounded(
        override val isLoading: Boolean,
        override val searchInput: String,
        val repoList: List<RepositoryResponse.Item>
    ) : SearchUiState
}