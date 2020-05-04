package br.com.fornaro.domain.repositories

import br.com.fornaro.domain.api.CategoryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val api: CategoryApi
) {

    suspend fun loadCategories() = withContext(Dispatchers.IO) {
        api.getCategories()
    }
}
