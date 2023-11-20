package com.kirik.repository.ui.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirik.repository.domain.model.Repository
import com.kirik.repository.domain.useCase.RepositoryUseCase
import com.kirik.repository.ui.RepositoryDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(savedStateHandle: SavedStateHandle, val useCase: RepositoryUseCase) :
    ViewModel() {
    private val owner: String = savedStateHandle[RepositoryDestinations.DetailRoute.OWNER] ?: ""
    private val name: String = savedStateHandle[RepositoryDestinations.DetailRoute.NAME] ?: ""

    init {
        getInfo()
    }

    fun getInfo() {
        viewModelScope.launch {
            val repository = useCase.getRepositoryByFull(
                owner, name
            )
            uiState.tryEmit(DetailsUiState.RepositoryState(repository))
        }
    }

    val uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.LoadingState)


}