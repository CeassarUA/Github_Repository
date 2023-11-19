package com.kirik.repository.domain

import androidx.paging.PagingData
import com.kirik.repository.data.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun search(text: String): RepositoryResponse
      fun searchRepository(text: String): Flow<PagingData<RepositoryResponse.Item>>
    suspend fun getDetails(text: String)
}