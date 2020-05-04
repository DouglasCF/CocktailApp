package com.fornaro.categories.di

import com.fornaro.categories.CategoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { CategoriesViewModel(get()) }
}

val categoriesModules = listOf(viewModelModules)