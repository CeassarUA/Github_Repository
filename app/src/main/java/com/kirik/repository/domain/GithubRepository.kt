package com.kirik.repository.domain

import com.kirik.repository.data.model.RepositoryResponse

interface GithubRepository {
    suspend fun search(text: String): RepositoryResponse
    suspend fun getDetails(text: String)
}