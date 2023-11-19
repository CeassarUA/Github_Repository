package com.kirik.repository.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.domain.GithubRepository
import com.kirik.repository.domain.useCase.RepositoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(val repositories: GithubRepository) : ViewModel() {

    fun getRepos(text:String) = repositories.searchRepository(text)
//    fun findRepo(text: String) {
//        viewModelScope.launch {
//            val response = if (text.isBlank()) RepositoryResponse(
//                null, null, null
//            ) else
//                useCase.getRepositories(text)
//            Log.d("SearchViewModel", response.toString())
//
//            viewModelState.update {
//                it.copy(
//                    repositories = response.items?.filterNotNull() ?: listOf()
//                )
//            }
//        }
//    }

    fun changeSearchText(searchText: String) {
        viewModelState.update {
            it.copy(searchInput = searchText)
        }
//        findRepo(searchText)
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
    val repositories: List<RepositoryResponse.Item> = listOf(),
//    val isArticleOpen: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    val searchInput: String = "",
) {

    fun toUiState() =

        if (repositories.isEmpty()) {
            Log.d("HomeRoute", "isEmpty")

            SearchUiState.NoPosts(
                isLoading = isLoading,
                searchInput = searchInput,
            )

        } else {
            SearchUiState.PostFounded(
                isLoading = isLoading,
                searchInput = searchInput,
                repoList = repositories

            )
        }
}