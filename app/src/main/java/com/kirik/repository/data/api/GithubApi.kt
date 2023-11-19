package com.kirik.repository.data.api

import com.kirik.repository.data.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): RepositoryResponse
}