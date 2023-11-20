package com.kirik.repository.domain

import androidx.paging.PagingData
import com.kirik.repository.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun search(text: String, page: Int, perPage: Int): List<Repository>
     fun searchRepository(text: String): Flow<PagingData<Repository>>
    suspend fun getRepositoryByFull(
        owner: String,
        name: String
    ): Repository
}