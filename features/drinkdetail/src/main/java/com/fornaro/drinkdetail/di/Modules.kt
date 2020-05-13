package com.fornaro.drinkdetail.di

import com.fornaro.drinkdetail.DrinkDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { (id: String) ->
        DrinkDetailViewModel(get(), id)
    }
}

val drinkDetailModules = listOf(viewModelModules)