package br.com.fornaro.domain.mappers

import br.com.fornaro.domain.models.CategoriesModel
import br.com.fornaro.domain.models.Category
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.domain.models.DrinksModel
import br.com.fornaro.domain.models.response.CategoriesResponse
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