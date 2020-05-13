package br.com.fornaro.domain.mappers

import br.com.fornaro.domain.models.CategoriesModel
import br.com.fornaro.domain.models.Category
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.domain.models.DrinksModel
import br.com.fornaro.domain.models.response.CategoriesResponse
import br.com.fornaro.domain.models.response.DrinkDetailModel
import br.com.fornaro.domain.models.response.DrinkDetailResponse
import br.com.fornaro.domain.models.response.DrinksResponse

fun CategoriesResponse.mapToCategoriesModel() = CategoriesModel(
    categoryList = drinks.map { Category(name = it.strCategory) }
)

fun DrinksResponse.mapToDrinksModel() = DrinksModel(
    drinkList = drinks.map {
        Drink(
            id = it.idDrink,
            name = it.strDrink,
            image = it.strDrinkThumb
        )
    }
)

fun DrinkDetailResponse.mapToDrinkDetailModel() = DrinkDetailModel(
    drinks.first().let {
        Drink(
            id = it.idDrink,
            name = it.strDrink,
            image = it.strDrinkThumb,
            category = it.strCategory,
            glass = it.strGlass,
            instructions = it.strInstructions,
            isAlcoholic = it.strAlcoholic == "Alcoholic",
            ingredients = mutableListOf<String>().apply {
                if (it.strIngredient1 != null) add(it.strIngredient1)
                if (it.strIngredient2 != null) add(it.strIngredient2)
                if (it.strIngredient3 != null) add(it.strIngredient3)
                if (it.strIngredient4 != null) add(it.strIngredient4)
                if (it.strIngredient5 != null) add(it.strIngredient5)
                if (it.strIngredient6 != null) add(it.strIngredient6)
                if (it.strIngredient7 != null) add(it.strIngredient7)
                if (it.strIngredient8 != null) add(it.strIngredient8)
                if (it.strIngredient9 != null) add(it.strIngredient9)
                if (it.strIngredient10 != null) add(it.strIngredient10)
                if (it.strIngredient11 != null) add(it.strIngredient11)
                if (it.strIngredient12 != null) add(it.strIngredient12)
                if (it.strIngredient13 != null) add(it.strIngredient13)
                if (it.strIngredient14 != null) add(it.strIngredient14)
                if (it.strIngredient15 != null) add(it.strIngredient15)
            }
        )
    }
)