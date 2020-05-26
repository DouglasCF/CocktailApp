package br.com.fornaro.domain.usecases

import br.com.fornaro.domain.repositories.DrinkDetailRepository

class LoadDrinkDetailUseCase(
    private val drinkDetailRepository: DrinkDetailRepository
) {

    suspend fun loadDrink(drinkId: String) = drinkDetailRepository.loadDrink(drinkId)
}