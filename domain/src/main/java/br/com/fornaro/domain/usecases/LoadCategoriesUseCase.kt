package br.com.fornaro.domain.usecases

import br.com.fornaro.domain.repositories.CategoryRepository

class LoadCategoriesUseCase(
    private val repository: CategoryRepository
) {

    suspend fun loadCategories() = repository.loadCategories()
}