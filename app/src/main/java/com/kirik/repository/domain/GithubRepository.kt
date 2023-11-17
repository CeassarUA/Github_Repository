package com.kirik.repository.domain

interface GithubRepository {
    suspend fun search(text: String)
    suspend fun getDetails(text: String)
}