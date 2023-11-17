package com.kirik.repository.ui.screen.details

sealed interface DetailsUiState {

    val isLoading: Boolean
    val errorMessages: List<String>

    data class NoData(
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
    ) : DetailsUiState

    data class Info(
        override val isLoading: Boolean,
        override val errorMessages: List<String>, ):DetailsUiState
    {

    }
}