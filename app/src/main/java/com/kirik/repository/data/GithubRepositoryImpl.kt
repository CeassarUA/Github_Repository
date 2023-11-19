package com.kirik.repository.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kirik.repository.RepositoryPagingSource
import com.kirik.repository.data.api.GithubApi
import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.domain.GithubRepository

class GithubRepositoryImpl(val api: GithubApi) : GithubRepository {
    override suspend fun search(text: String): RepositoryResponse {
        return api.searchRepo(text, 1, 2)
    }

    override  fun searchRepository(text: String)=
         Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RepositoryPagingSource(text, api)
            }
        ).flow


    override suspend fun getDetails(text: String) {
    }
}