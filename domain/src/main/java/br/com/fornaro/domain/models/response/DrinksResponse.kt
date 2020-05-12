package br.com.fornaro.domain.models.response

data class DrinksResponse(
    val drinks: List<DrinkResponse>
)

data class DrinkResponse(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)