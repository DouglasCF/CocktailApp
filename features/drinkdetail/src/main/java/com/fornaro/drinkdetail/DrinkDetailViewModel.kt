package com.fornaro.drinkdetail

import androidx.lifecycle.viewModelScope
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State
import br.com.fornaro.domain.usecases.DrinkDetailUseCases
import kotlinx.coroutines.launch

class DrinkDetailViewModel(
    private val drinkDetailUseCases: DrinkDetailUseCases,
    private val drinkId: String
) : BaseViewModel() {

    init {
        loadDrink()
    }

    fun loadDrink() = viewModelScope.launch {
        _state.value = State.Loading
        _state.value = State.runBlocking { drinkDetailUseCases.loadDrink(drinkId) }
    }
}
