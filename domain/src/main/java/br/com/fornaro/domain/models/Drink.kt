package br.com.fornaro.domain.models

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
    val ingredientsAsString
        get() = ingredients?.joinToString(separator = ", ")
}