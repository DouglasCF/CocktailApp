package br.com.fornaro.domain.models

data class DrinksModel(
    val drinkList: List<Drink>
)

data class Drink(
    val id: String,
    val name: String,
    val image: String,
    val instructions: String? = null,
    val glass: String? = null,
    val isAlcoholic: Boolean? = null,
    val category: String? = null,
    val ingredients: List<String>? = null
) {
    val ingredientsString
        get() = ingredients?.joinToString(separator = ", ")
}