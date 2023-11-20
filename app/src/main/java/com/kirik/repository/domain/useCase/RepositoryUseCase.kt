package com.kirik.repository.domain.useCase

import com.kirik.repository.domain.GithubRepository
import kotlinx.coroutines.flow.flow

class RepositoryUseCase(val repository: GithubRepository) {

    operator fun invoke(text: String) = repository.searchRepository(text)
    suspend fun getRepositoryByFull(
        owner: String,
        name: String
    ) = repository.getRepositoryByFull(owner, name)

}