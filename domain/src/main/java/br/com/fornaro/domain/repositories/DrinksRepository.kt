package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.DrinksApi
import br.com.fornaro.domain.mappers.mapToDrinksModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DrinksRepository(
    private val api: DrinksApi
) {

    suspend fun loadDrinks(categoryName: String) = withContext(Dispatchers.IO) {
        delay(5000) // Just to see the beautiful loading animation :)
        api.getDrinks(categoryName)
            .mapToDrinksModel()
    }
}