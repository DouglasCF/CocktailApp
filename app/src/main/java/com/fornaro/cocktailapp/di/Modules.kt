package com.fornaro.cocktailapp.di

import br.com.fornaro.domain.di.domainModules
import com.fornaro.categories.categoriesModules

val allModules = categoriesModules +
        domainModules