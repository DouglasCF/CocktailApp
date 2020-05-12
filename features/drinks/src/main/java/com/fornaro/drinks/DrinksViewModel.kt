package com.fornaro.drinks

import androidx.lifecycle.ViewModel
import br.com.fornaro.domain.usecases.DrinksUseCases

class DrinksViewModel(
    private val drinksUseCases: DrinksUseCases
) : ViewModel() {

}
