package com.kirik.repository.ui.screen.details

import com.kirik.repository.domain.model.Repository

sealed class DetailsUiState() {
        data object LoadingState : DetailsUiState()
        data class RepositoryState(
            val repository: Repository
        ) : DetailsUiState()
    }