package com.fornaro.categories

import androidx.lifecycle.viewModelScope
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State
import br.com.fornaro.domain.usecases.CategoryUseCases
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoryUseCases: CategoryUseCases
) : BaseViewModel() {

    init {
        loadCategories()
    }

    private fun loadCategories() = viewModelScope.launch {
        _state.value = State.Loading
        _state.value = State.runBlocking { categoryUseCases.loadCategories() }
    }
}
