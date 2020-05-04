package com.fornaro.cocktailapp.di

import br.com.fornaro.domain.di.domainModules
import com.fornaro.categories.di.categoriesModules

val allModules = categoriesModules +
        domainModules