package com.kirik.repository.ui.screen.search

data class SearchViewModelState(

    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    var items: List<String>
) {

    fun toUiState() =
        if (items.isEmpty()) {
            SearchUiState.NoItems(
                isLoading = isLoading,
            )
        } else {
            SearchUiState.ItemsFounded(
                isLoading = isLoading,
                items
            )
        }
}