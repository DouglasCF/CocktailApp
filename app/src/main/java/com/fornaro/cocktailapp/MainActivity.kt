package com.fornaro.cocktailapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.categoriesFragment,
                R.id.searchFragment,
                R.id.favoritesFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        visibilityNavElements(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navController).navigateUp() || super.onSupportNavigateUp()
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.categoriesFragment,
                R.id.searchFragment,
                R.id.favoritesFragment -> bottomNavigationView.isVisible = true
                else -> bottomNavigationView.isVisible = false
            }
        }
    }
}
