package com.kirik.repository.data

import com.kirik.repository.data.api.GithubApi
import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.domain.GithubRepository

class GithubRepositoryImpl(val api: GithubApi) : GithubRepository {
    override suspend fun search(text: String): RepositoryResponse {
        return api.searchRepo(text, 1, 10)
    }

    override suspend fun getDetails(text: String) {
    }
}