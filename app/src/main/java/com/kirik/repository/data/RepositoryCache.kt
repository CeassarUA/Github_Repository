package com.kirik.repository.data

import androidx.collection.LruCache
import com.kirik.repository.domain.model.Repository

class RepositoryCache   {

    private val cache = LruCache<String, Repository>(300)

    fun getRepo(value: String) = cache[value]

    fun saveRepos(repos: List<Repository>) {
        repos.forEach { cache.put(it.fullName, it) }
    }
}