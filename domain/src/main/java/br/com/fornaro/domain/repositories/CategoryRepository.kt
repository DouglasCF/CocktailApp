package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.CategoryApi
import br.com.fornaro.domain.mappers.mapToCategoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val api: CategoryApi
) {

    suspend fun loadCategories() = withContext(Dispatchers.IO) {
        delay(7000) // Just to see the beautiful loading animation :)
        api.getCategories()
            .mapToCategoriesModel()
    }
}
