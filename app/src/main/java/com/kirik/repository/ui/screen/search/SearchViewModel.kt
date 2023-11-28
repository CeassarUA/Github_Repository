package com.kirik.repository.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirik.repository.domain.useCase.RepositoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel(val searchGithubUseCase: RepositoryUseCase) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        SearchViewModelState(
            isLoading = true,
           items = mutableListOf()
        )
    )
    val uiState: StateFlow<SearchUiState> = viewModelState
        .map(SearchViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    var searchResult  =
        viewModelState.map { it.items }
            .distinctUntilChanged()
            .debounce(200)
//            .flatMapLatest {
//                return@flatMapLatest SEARCH
//            }
//            .cachedIn(viewModelScope)

    fun startSearch( isSearch:Boolean) {
        viewModelState.update {
            it.copy(isLoading = false)
        }
    }
}

