package br.com.fornaro.domain.models.response

data class CategoriesResponse(
    val drinks: List<CategoryResponse>
)

data class CategoryResponse(
    val strCategory: String
)