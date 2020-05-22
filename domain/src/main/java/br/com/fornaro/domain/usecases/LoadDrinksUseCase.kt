package br.com.fornaro.domain.usecases

import br.com.fornaro.domain.repositories.DrinksRepository

class LoadDrinksUseCase(
    private val drinksRepository: DrinksRepository
) {

    suspend fun loadDrinks(categoryName: String) = drinksRepository.loadDrinks(categoryName)
}