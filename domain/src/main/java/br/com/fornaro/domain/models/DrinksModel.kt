package br.com.fornaro.domain.models

data class DrinksModel(
    val drinkList: List<Drink>
)

data class Drink(
    val id: String,
    val name: String,
    val image: String
)