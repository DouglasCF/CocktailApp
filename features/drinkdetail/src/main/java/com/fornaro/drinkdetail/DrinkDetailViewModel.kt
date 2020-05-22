package com.fornaro.drinkdetail

import androidx.lifecycle.viewModelScope
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.domain.usecases.LoadDrinkDetailUseCase
import kotlinx.coroutines.launch

class DrinkDetailViewModel(
    private val loadDrinkDetailUseCase: LoadDrinkDetailUseCase,
    private val drinkId: String
) : BaseViewModel<Drink>() {

    init {
        loadDrink()
    }

    fun loadDrink() = viewModelScope.launch {
        state.value = State.Loading()
        state.value = State.runBlocking { loadDrinkDetailUseCase.loadDrink(drinkId) }
    }
}
