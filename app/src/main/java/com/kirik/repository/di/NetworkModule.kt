package com.kirik.repository.di

import com.kirik.repository.data.RepositoryImpl
import com.kirik.repository.domain.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
}

private const val BASE_URL = "https://api.github.com"

