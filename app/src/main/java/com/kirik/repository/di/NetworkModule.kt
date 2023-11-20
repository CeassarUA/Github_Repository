package com.kirik.repository.di

import com.kirik.repository.data.GithubRepositoryImpl
import com.kirik.repository.data.api.GithubApi
import com.kirik.repository.domain.GithubRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { createGithubApi() }
    single<GithubRepository> { GithubRepositoryImpl(get(), get()) }
}

private const val BASE_URL = "https://api.github.com"

fun createGithubApi(): GithubApi {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApi::class.java)
}