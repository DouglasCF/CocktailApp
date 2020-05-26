package com.fornaro.drinks

import androidx.lifecycle.viewModelScope
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.domain.usecases.LoadDrinksUseCase
import kotlinx.coroutines.launch

class DrinksViewModel(
    private val loadDrinksUseCase: LoadDrinksUseCase,
    private val categoryName: String
) : BaseViewModel<List<Drink>>() {

    init {
        loadDrinks()
    }

    fun loadDrinks() = viewModelScope.launch {
        state.value = State.Loading()
        state.value = State.runBlocking { loadDrinksUseCase.loadDrinks(categoryName) }
    }
}
