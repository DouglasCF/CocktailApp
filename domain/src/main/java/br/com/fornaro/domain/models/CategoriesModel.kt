package br.com.fornaro.domain.models

data class CategoriesModel(
    val categoryList: List<Category>
)

data class Category(
    val name: String
)