package com.kirik.repository.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kirik.repository.domain.useCase.RepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel(val searchGithubUseCase: RepositoryUseCase) : ViewModel() {




    private val viewModelState = MutableStateFlow(
        SearchViewModelState(
            isLoading = true,

        )
    )
    val uiState: StateFlow<SearchUiState> = viewModelState
        .map(SearchViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    var searchResult  =
        viewModelState.map { it.searchInput }
            .distinctUntilChanged()
            .debounce(200)
            .flatMapLatest {
                return@flatMapLatest searchGithubUseCase(it)
            }.cachedIn(viewModelScope)


    fun changeSearchText(searchText: String) {
        viewModelState.update {
            it.copy(searchInput = searchText)
        }
    }
}

