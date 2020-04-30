package com.fornaro.cocktailapp

import android.app.Application
import com.fornaro.cocktailapp.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startDependenceInjection()
    }

    private fun startDependenceInjection() = startKoin {
        androidContext(applicationContext)
        modules(allModules)
    }
}