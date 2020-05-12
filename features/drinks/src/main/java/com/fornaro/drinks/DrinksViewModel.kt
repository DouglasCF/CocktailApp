package com.fornaro.drinks

import androidx.lifecycle.viewModelScope
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State
import br.com.fornaro.domain.usecases.DrinksUseCases
import kotlinx.coroutines.launch

class DrinksViewModel(
    private val drinksUseCases: DrinksUseCases,
    private val categoryName: String
) : BaseViewModel() {

    init {
        loadDrinks()
    }

    fun loadDrinks() = viewModelScope.launch {
        _state.value = State.Loading
        _state.value = State.runBlocking { drinksUseCases.loadDrinks(categoryName) }
    }
}
