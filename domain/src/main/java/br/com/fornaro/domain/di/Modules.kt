package br.com.fornaro.domain.di

import org.koin.dsl.module

private val useCasesModules = module {
}

private val repositoriesModules = module {
}

val modules = listOf(
    useCasesModules,
    repositoriesModules
)