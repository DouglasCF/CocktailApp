package com.fornaro.cocktailapp.di

import br.com.fornaro.domain.di.domainModules
import com.fornaro.categories.di.categoriesModules
import com.fornaro.drinks.di.drinksModules
import com.fornaro.favorites.di.favoritesModules
import com.fornaro.search.di.searchModules

val allModules = domainModules +
        categoriesModules +
        searchModules +
        favoritesModules+
        drinksModules