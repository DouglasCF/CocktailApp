package com.fornaro.favorites.di

import com.fornaro.favorites.FavoritesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { FavoritesViewModel(get()) }
}

val favoritesModules = listOf(viewModelModules)