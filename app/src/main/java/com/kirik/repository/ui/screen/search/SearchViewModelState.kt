package com.kirik.repository.ui.screen.search

import com.kirik.repository.data.model.RepositoryResponse

data class SearchViewModelState(

    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    val searchInput: String = "",
) {

    fun toUiState() =
        if (searchInput.isBlank()) {
            SearchUiState.NoPosts(
                isLoading = isLoading,
                searchInput = searchInput,
            )
        } else {
            SearchUiState.PostFounded(
                isLoading = isLoading,
                searchInput = searchInput

            )
        }
}