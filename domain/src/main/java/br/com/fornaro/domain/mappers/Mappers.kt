package br.com.fornaro.domain.mappers

import br.com.fornaro.domain.models.CategoriesModel
import br.com.fornaro.domain.models.Category
import br.com.fornaro.domain.models.response.CategoriesResponse

fun CategoriesResponse.mapToCategoriesModel() = CategoriesModel(
    categoryList = drinks.map { Category(name = it.strCategory) }
)
