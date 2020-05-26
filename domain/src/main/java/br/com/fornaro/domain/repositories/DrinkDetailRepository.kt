package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.DrinkDetailApi
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.domain.api.response.DrinkDetailResponse
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

    private fun DrinkDetailResponse.mapToDrinkDetailModel() = drinks.first().let {
        Drink(
            id = it.idDrink,
            name = it.strDrink,
            image = it.strDrinkThumb,
            category = it.strCategory,
            glass = it.strGlass,
            instructions = it.strInstructions,
            isAlcoholic = it.strAlcoholic == "Alcoholic",
            ingredients = listOfNotNull(
                it.strIngredient1, it.strIngredient2, it.strIngredient3, it.strIngredient4, it.strIngredient5, it.strIngredient6,
                it.strIngredient7, it.strIngredient8, it.strIngredient9, it.strIngredient10, it.strIngredient11, it.strIngredient12,
                it.strIngredient13, it.strIngredient14, it.strIngredient15
            )
        )
    }
}
