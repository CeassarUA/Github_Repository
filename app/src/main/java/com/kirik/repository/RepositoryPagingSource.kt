package com.kirik.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kirik.repository.data.api.GithubApi
import com.kirik.repository.data.mapToRepository
import com.kirik.repository.domain.model.Repository

class RepositoryPagingSource(
    private val query: String,
    private val githubApi: GithubApi,
) : PagingSource<Int, Repository>() {

    companion object {
        const val PER_PAGE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val page = params.key ?: 1
            val response = githubApi.searchRepo(
                page = page,
                perPage = PER_PAGE,
                query = query
            ).items.map {
                it.mapToRepository()
            }

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}