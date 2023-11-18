package com.kirik.repository.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.domain.useCase.RepositoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(val useCase: RepositoryUseCase) : ViewModel() {

    val repositoryFlow = MutableStateFlow<RepositoryResponse?>(null)

    fun findRepo(text: String) {
        viewModelScope.launch {
            repositoryFlow.tryEmit(useCase.getRepositories(text))
        }
    }

    fun changeSearchText(searchText: String) {
        viewModelState.update {
            it.copy(searchInput = searchText)
        }
        findRepo(searchText)
    }

    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
//            isArticleOpen = preSelectedPostId != null
        )
    )
    val uiState: StateFlow<SearchUiState> = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )
}

private data class HomeViewModelState(

    //todo change to feed
    val postsFeed: Any? = null,
//    val isArticleOpen: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    val searchInput: String = "",
) {
    fun toUiState() =
        if (postsFeed == null) {
            SearchUiState.NoPosts(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
            )
        } else {
            //todo state
            SearchUiState.NoPosts(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
            )
        }
}