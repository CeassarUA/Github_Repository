package com.kirik.repository.di

import com.kirik.repository.ui.screen.details.DetailsViewModel
import com.kirik.repository.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailsViewModel)
}