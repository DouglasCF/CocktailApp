package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.DrinkDetailApi
import br.com.fornaro.domain.mappers.mapToDrinkDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DrinkDetailRepository(
    private val api: DrinkDetailApi
) {

    suspend fun loadDrink(drinkId: String) = withContext(Dispatchers.IO) {
        delay(5000) // Just to see the beautiful loading animation :)
        api.getDrink(drinkId)
            .mapToDrinkDetailModel()
    }
}