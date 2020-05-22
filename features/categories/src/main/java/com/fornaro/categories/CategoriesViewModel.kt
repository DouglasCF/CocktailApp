package com.fornaro.categories

import androidx.lifecycle.viewModelScope
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State
import br.com.fornaro.domain.usecases.LoadCategoriesUseCase
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) : BaseViewModel<List<String>>() {

    init {
        loadCategories()
    }

    fun loadCategories() = viewModelScope.launch {
        state.value = State.Loading()
        state.value = State.runBlocking { loadCategoriesUseCase.loadCategories() }
    }
}
