package com.kirik.repository.di

import com.kirik.repository.data.RepositoryCache
import com.kirik.repository.domain.useCase.RepositoryUseCase
import org.koin.dsl.module

val appModule = module {
    single { RepositoryUseCase(get()) }
    single { RepositoryCache() }
}