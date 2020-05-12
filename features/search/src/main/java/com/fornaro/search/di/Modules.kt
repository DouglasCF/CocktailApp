package com.fornaro.search.di

import com.fornaro.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { SearchViewModel(get()) }
}

val searchModules = listOf(viewModelModules)