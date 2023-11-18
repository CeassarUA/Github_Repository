package com.kirik.repository.domain.useCase

import com.kirik.repository.domain.GithubRepository

class RepositoryUseCase(val repository: GithubRepository) {

     suspend fun getRepositories(text: String)  = repository.search(text)
}