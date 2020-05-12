package com.fornaro.search

import androidx.lifecycle.ViewModel
import br.com.fornaro.domain.usecases.SearchUseCases

class SearchViewModel(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
}
