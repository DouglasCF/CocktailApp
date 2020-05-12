package com.fornaro.favorites

import androidx.lifecycle.ViewModel
import br.com.fornaro.domain.usecases.FavoritesUseCases

class FavoritesViewModel(
    private val favoritesUseCases: FavoritesUseCases
) : ViewModel() {
}
