package br.com.fornaro.domain.usecases

import br.com.fornaro.domain.repositories.DrinkDetailRepository

class DrinkDetailUseCases(
    private val drinkDetailRepository: DrinkDetailRepository
) {

    suspend fun loadDrink(drinkId: String) = drinkDetailRepository.loadDrink(drinkId)
}