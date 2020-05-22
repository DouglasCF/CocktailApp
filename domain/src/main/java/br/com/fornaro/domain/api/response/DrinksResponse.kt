package br.com.fornaro.domain.api.response

data class DrinksResponse(
    val drinks: List<DrinkResponse>
)

data class DrinkResponse(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strCategory: String? = null,
    val strGlass: String? = null,
    val strAlcoholic: String? = null,
    val strInstructions: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null
)