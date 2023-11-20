package com.kirik.repository.data

import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.domain.model.Repository

fun RepositoryResponse.Item.mapToRepository() = Repository(
    id = this.id,
    fullName = this.fullName ?: "",
    name = this.name ?: "",
    description = this.description,
    watchersCount = this.watchersCount ?: 0,
    stars = this.stargazersCount ?: 0,
    image = this.owner.avatarUrl,
)
