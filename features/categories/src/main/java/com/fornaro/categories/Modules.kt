package com.fornaro.categories

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { CategoriesViewModel() }
}

val categoriesModules = listOf(viewModelModules)