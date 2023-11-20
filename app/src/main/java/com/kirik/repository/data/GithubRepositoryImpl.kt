package com.kirik.repository.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kirik.repository.RepositoryPagingSource
import com.kirik.repository.data.api.GithubApi
import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.domain.GithubRepository
import com.kirik.repository.domain.model.Repository

class GithubRepositoryImpl(
    private val api: GithubApi,
    private val cache: RepositoryCache
) : GithubRepository {

    override suspend fun search(text: String, page: Int, perPage: Int): List<Repository> {

        val repositories = api.searchRepo(text, page, perPage).items.map {
            it.mapToRepository()
        }
        cache.saveRepos(repositories)
        return repositories
    }

    private suspend fun getRepositoryByFullAddressRemote(
        owner: String,
        name: String
    ): RepositoryResponse.Item {
        return api.getRepositoryByFull(owner, name)
    }

    override fun searchRepository(text: String) =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RepositoryPagingSource(text, api)
            }
        ).flow

    override suspend fun getRepositoryByFull(
        owner: String,
        name: String
    ): Repository {
        val cached = cache.getRepo("$owner/$name")
        return cached ?: getRepositoryByFullAddressRemote(
            owner, name
        ).mapToRepository()

    }


}