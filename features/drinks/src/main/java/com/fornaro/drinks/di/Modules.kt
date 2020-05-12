package com.fornaro.drinks.di

import com.fornaro.drinks.DrinksViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { (categoryName: String) ->
        DrinksViewModel(get(), categoryName)
    }
}

val drinksModules = listOf(viewModelModules)