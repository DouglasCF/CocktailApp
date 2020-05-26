package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.DrinksApi
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.domain.api.response.DrinksResponse
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

    private fun DrinksResponse.mapToDrinksModel() = drinks.map { Drink(id = it.idDrink, name = it.strDrink, image = it.strDrinkThumb) }
}
