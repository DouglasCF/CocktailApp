package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.CategoryApi
import br.com.fornaro.domain.api.response.CategoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val api: CategoryApi
) {

    suspend fun loadCategories() = withContext(Dispatchers.IO) {
        delay(5000) // Just to see the beautiful loading animation :)
        api.getCategories()
            .mapToCategoriesModel()
    }

    private fun CategoriesResponse.mapToCategoriesModel() = drinks.map { it.strCategory }
}
