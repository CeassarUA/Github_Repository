package com.kirik.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kirik.repository.data.api.GithubApi
import com.kirik.repository.data.model.RepositoryResponse

class RepositoryPagingSource(
    val query: String,
    private val newsApiService: GithubApi,
) : PagingSource<Int, RepositoryResponse.Item>() {

    companion object {
        const val PER_PAGE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryResponse.Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryResponse.Item> {
        return try {
            val page = params.key ?: 1
            val response = newsApiService.searchRepo(
                page = page,
                perPage = PER_PAGE,
                query = query
            )

            LoadResult.Page(
                data = response.items,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.items.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}